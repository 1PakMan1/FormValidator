package ru.parkman.formvalidator

import com.badoo.mvicore.element.Reducer
import ru.parkman.formvalidator.form.CommonForm

/**
 * Created by Igor Park on 13/09/2020.
 */
internal class FormReducer<Form : CommonForm> :
    Reducer<FormFeature.State, FormFeature.Effect<Form>> {

    override fun invoke(
        state: FormFeature.State,
        effect: FormFeature.Effect<Form>
    ): FormFeature.State {
        return when (effect) {
            is FormFeature.Effect.FieldInFocus<Form> -> {
                state.copy(
                    fieldInFocus = effect.fieldInFocus
                )
            }

            is FormFeature.Effect.ResultFormState<Form> -> {
                state.copy(
                    isEmpty = effect.isEmpty,
                    formState = effect.formState
                )
            }
            is FormFeature.Effect.FieldOutOfFocus<Form> -> {
                state.copy(
                    formState = effect.formState
                )
            }
        }
    }
}
