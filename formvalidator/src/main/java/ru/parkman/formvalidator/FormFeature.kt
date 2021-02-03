package ru.parkman.formvalidator

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.feature.ActorReducerFeature
import ru.parkman.formvalidator.field.FormFieldState
import ru.parkman.formvalidator.form.CommonForm

/**
 * Created by Igor Park on 10/07/2020.
 */
typealias FormState = Map<Qualifier, FormFieldState>

typealias Qualifier = Any

class FormFeature<Form : CommonForm>(
    initialState: State,
    actor: Actor<State, Wish<Form>, Effect<Form>>
) : ActorReducerFeature<
    FormFeature.Wish<Form>,
    FormFeature.Effect<Form>,
    FormFeature.State,
    Nothing>(
    initialState = initialState,
    actor = actor,
    reducer = FormReducer(),
    bootstrapper = null,
    newsPublisher = null
) {
    data class State(
        val formState: FormState,
        val isEmpty: Boolean,
        val fieldInFocus: Qualifier? = null,
        val withDelayedState: Boolean = true
    )

    sealed class Wish<Form> {
        data class SwitchField<Form>(
            val field: Qualifier,
            val hasFocus: Boolean
        ) : Wish<Form>()

        data class Input<Form>(
            val form: Form
        ) : Wish<Form>()
    }

    sealed class Effect<Form> {
        data class FieldInFocus<Form>(
            val fieldInFocus: Qualifier?
        ) : Effect<Form>()

        data class FieldOutOfFocus<Form>(
            val formState: FormState
        ) : Effect<Form>()

        data class ResultFormState<Form>(
            val isEmpty: Boolean,
            val formState: FormState
        ) : Effect<Form>()
    }
}
