package ru.parkman.formvalidator

import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import ru.parkman.formvalidator.form.LoginForm

class MainActivityBindings(
    view: MainActivity,
    private val formFeature: FormFeature<LoginForm>
) : AndroidBindings<MainActivity>(view) {
    override fun setup(view: MainActivity) {
        binder.bind(formFeature to view using ViewModelTransformer())
        binder.bind(view to formFeature using UiEventTransformer())
    }

    class UiEventTransformer : (MainActivity.UiEvent<LoginForm>) -> FormFeature.Wish<LoginForm>? {
        override fun invoke(event: MainActivity.UiEvent<LoginForm>): FormFeature.Wish<LoginForm>? {
            return when (event) {
                is MainActivity.UiEvent.Input -> {
                    FormFeature.Wish.Input(
                        form = event.form
                    )
                }

                is MainActivity.UiEvent.SwitchField -> {
                    FormFeature.Wish.SwitchField(
                        field = event.fieldType,
                        hasFocus = event.hasFocus
                    )
                }
            }
        }
    }

    class ViewModelTransformer : (FormFeature.State) -> MainActivity.LoginState {

        override fun invoke(formFeatureState: FormFeature.State): MainActivity.LoginState =
            with(formFeatureState) {
                return MainActivity.LoginState(
                    formState = formState
                )
            }
    }
}