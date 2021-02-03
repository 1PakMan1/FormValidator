package ru.parkman.formvalidator

import android.os.Bundle
import io.reactivex.functions.Consumer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import kotlinx.android.synthetic.main.activity_main.*
import ru.parkman.formvalidator.field_impl.LoginField
import ru.parkman.formvalidator.field_view_impl.FieldViewImpl
import ru.parkman.formvalidator.utils.textChanges
import ru.parkman.formvalidator.form.LoginForm
import ru.parkman.formvalidator.form_validation_impl.LoginFormValidator
import ru.parkman.formvalidator.validation_result_to_form_state.LoginFormStateMapper
import ru.parkman.formvalidator.qualifier.LoginFieldType
import ru.parkman.formvalidator.field.FormFieldState
import ru.parkman.formvalidator.form.FormFieldsHolder

/**
 * Created by Igor Park on 24/09/2020.
 */
class MainActivity : ObservableSourceActivity<MainActivity.UiEvent<LoginForm>>(),
    Consumer<MainActivity.LoginState> {
    private lateinit var fieldsHolder: FormFieldsHolder<LoginField>
    private var loginFormSubscription: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLoginForm()
        listenFieldFocus()
        listenLoginForm()
        bindings.setup(this)
    }

    private fun initLoginForm() {
        val formFields = listOf(
            LoginField.Email(
                fieldView = FieldViewImpl(
                    input = emailInput,
                    errorMessage = emailError
                )
            ),
            LoginField.Password(
                fieldView = FieldViewImpl(
                    input = passwordInput,
                    errorMessage = passwordError
                )
            )
        )
        fieldsHolder = FormFieldsHolder(formFields)
    }

    private fun listenFieldFocus() {
        emailInput.setOnFocusChangeListener { _, hasFocus ->
            onNext(
                UiEvent.SwitchField(
                    fieldType = LoginFieldType.Email,
                    hasFocus = hasFocus
                )
            )
        }

        passwordInput.setOnFocusChangeListener { _, hasFocus ->
            onNext(
                UiEvent.SwitchField(
                    fieldType = LoginFieldType.Password,
                    hasFocus = hasFocus
                )
            )
        }
    }

    private fun listenLoginForm() {
        loginFormSubscription = Observable.combineLatest(
            emailInput.textChanges(),
            passwordInput.textChanges(),
            BiFunction(::LoginForm)
        )
            .map { UiEvent.Input(it) }
            .subscribe {
                onNext(it)
            }
    }

    private val bindings: MainActivityBindings = MainActivityBindings(
        view = this,
        formFeature = FormFeature(
            initialState = FormFeature.State(
                isEmpty = true,
                formState = mapOf(
                    LoginFieldType.Email to FormFieldState.ErrorState(
                        fieldError = LoginFieldError.Email.Empty,
                        isDisplayable = false
                    ),
                    LoginFieldType.Password to FormFieldState.ErrorState(
                        fieldError = LoginFieldError.Password.Empty,
                        isDisplayable = false
                    )
                ),
                fieldInFocus = null,
                withDelayedState = true
            ),
            actor = FormActor(
                validator = LoginFormValidator(),
                formStateMapper = LoginFormStateMapper()
            )
        )
    )

    sealed class UiEvent<Form> {
        data class SwitchField<Form>(
            val fieldType: Qualifier,
            val hasFocus: Boolean
        ) : UiEvent<Form>()

        data class Input<Form>(
            val form: Form
        ) : UiEvent<Form>()
    }

    data class LoginState(
        val formState: FormState = emptyMap()
    )

    override fun accept(loginState: LoginState) {
        fieldsHolder.applyState(loginState.formState)
    }
}
