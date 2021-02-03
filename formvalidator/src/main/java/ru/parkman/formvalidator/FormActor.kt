package ru.parkman.formvalidator

import com.badoo.mvicore.element.Actor
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import ru.parkman.formvalidator.field.FormFieldState
import ru.parkman.formvalidator.form.CommonForm
import ru.parkman.formvalidator.form_validation.ValidationResult
import ru.parkman.formvalidator.form_validation.Validator
import ru.parkman.formvalidator.utils.Mapper2
import ru.parkman.formvalidator.utils.Rx2Observable
import ru.parkman.formvalidator.utils.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Igor Park on 10/07/2020.
 */

internal fun <T> Observable<T>.toRx2Observable(): io.reactivex.Observable<T> {
    return RxJavaBridge.toV2Observable(this)
}
internal fun <T> Observable<T>.zipWithTimer(ms: Long): Observable<T> {
    return this.zipWith(
        Observable.timer(ms, TimeUnit.MILLISECONDS), BiFunction { t, _ -> t }
    )
}
class FormActor<Form : CommonForm, Result : ValidationResult>(
    private val validator: Validator<Form, Result>,
    private val formStateMapper: Mapper2<Result, FormState, FormState>
) : Actor<
    FormFeature.State,
    FormFeature.Wish<Form>,
    FormFeature.Effect<Form>
    > {

    private var delayedDisposable: Disposable? = null

    override fun invoke(
        state: FormFeature.State,
        action: FormFeature.Wish<Form>
    ): Rx2Observable<out FormFeature.Effect<Form>> {
        return getEffectObservable(state, action)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.ui())
            .toRx2Observable()
    }

    private fun getEffectObservable(
        state: FormFeature.State,
        action: FormFeature.Wish<Form>
    ): Observable<FormFeature.Effect<Form>> {
        return when (action) {
            is FormFeature.Wish.SwitchField -> {
                val effect: FormFeature.Effect<Form> = if (action.hasFocus) {
                    FormFeature.Effect.FieldInFocus(
                        fieldInFocus = action.field
                    )
                } else {
                    FormFeature.Effect.FieldOutOfFocus(
                        formState = state.formState
                            .updateFieldErrorVisibility(
                                field = action.field,
                                showState = state.isEmpty.not()
                            )
                    )
                }
                Observable.just(effect)
            }

            is FormFeature.Wish.Input<Form> -> {
                emitValidationResult(
                    newForm = action.form,
                    currentFormState = state.formState,
                    fieldInFocus = state.fieldInFocus,
                    withDelay = state.withDelayedState
                )
            }
        }
    }

    private fun emitValidationResult(
        newForm: Form,
        currentFormState: FormState,
        fieldInFocus: Qualifier?,
        withDelay: Boolean
    ): Observable<FormFeature.Effect<Form>> {
        delayedDisposable?.dispose()

        val formStateErrorInvisible = validateForm(
            form = newForm,
            currentFormState = currentFormState
        )
            .updateFieldErrorVisibility(
                field = fieldInFocus,
                showState = false
            )

        val isErrorVisible = newForm.isEmpty().not()

        val delayDuration = if (withDelay) MIN_STATE_DELAY else 0L

        val formStateErrorVisible = formStateErrorInvisible.updateFieldErrorVisibility(
            field = fieldInFocus,
            showState = isErrorVisible
        )

        val formStatesSequence = listOfNotNull(
            formStateErrorInvisible,
            formStateErrorVisible.takeIf { isErrorVisible }
        ).map { newFormState ->
            FormFeature.Effect.ResultFormState<Form>(
                newForm.isEmpty(),
                newFormState
            )
        }

        return Observable.fromIterable<FormFeature.Effect<Form>>(formStatesSequence)
            .delayAfterFirst(delayDuration)
            .doOnSubscribe {
                delayedDisposable = it
            }
    }

    private fun FormState.updateFieldErrorVisibility(
        field: Qualifier?,
        showState: Boolean
    ): Map<Qualifier, FormFieldState> {

        if (field == null) return this

        val updatedFormState = (this.takeIf { it.isNotEmpty() })
            ?.toMutableMap()
            ?: error("Form state is empty")

        val fieldState = updatedFormState[field]
            ?: error("Unknown field type: $field")

        updatedFormState[field] = fieldState.setDisplayable(isDisplayable = showState)

        return updatedFormState
    }

    private fun validateForm(form: Form, currentFormState: FormState): FormState {
        return validator.validate(form)
            .let { validationResult ->
                formStateMapper.invoke(validationResult, currentFormState)
            }
    }

    private fun <T> Observable<T>.delayAfterFirst(delay: Long): Observable<T> {
        var counter = 0
        return flatMap { item ->
            Observable.just(item)
                .let { observable ->
                    if (counter == 0) {
                        counter += 1
                        observable
                    } else {
                        observable.zipWithTimer(delay)
                    }
                }
        }
    }

    companion object {
        private const val MIN_STATE_DELAY = 1000L
    }
}
