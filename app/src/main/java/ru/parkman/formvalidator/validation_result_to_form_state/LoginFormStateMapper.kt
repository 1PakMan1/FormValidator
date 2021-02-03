package ru.parkman.formvalidator.validation_result_to_form_state

import ru.parkman.formvalidator.LoginFieldError
import ru.parkman.formvalidator.form_validation_impl.LoginFormValidationResult
import ru.parkman.formvalidator.qualifier.LoginFieldType
import ru.parkman.formvalidator.FormState
import ru.parkman.formvalidator.Qualifier
import ru.parkman.formvalidator.field.FieldError
import ru.parkman.formvalidator.field.FormFieldState
import ru.parkman.formvalidator.mappers.DefaultFormattedErrorMapper
import ru.parkman.formvalidator.utils.Mapper2

class LoginFormStateMapper : Mapper2<LoginFormValidationResult, FormState, FormState> {
    override fun invoke(
        validationResult: LoginFormValidationResult,
        currentFormState: FormState
    ): Map<Qualifier, FormFieldState> {
        return currentFormState.mapValues { (field, fieldState) ->
            val fieldError = getFieldError(
                validationResult = validationResult,
                field = field as LoginFieldType
            )
            fieldState.setError(fieldError)
        }
    }

    private fun getFieldError(
        field: LoginFieldType,
        validationResult: LoginFormValidationResult
    ): FieldError? {
        return when (field) {
            is LoginFieldType.Email -> {
                val result = validationResult.email
                DefaultFormattedErrorMapper.map(
                    validationResult = result,
                    onEmpty = { LoginFieldError.Email.Empty },
                    onLengthNotAcceptable = { LoginFieldError.Email.MaxLengthExceeded },
                    onIncorrectFormat = { LoginFieldError.Email.IncorrectFormat },
                )
            }

            is LoginFieldType.Password -> {
                val result = validationResult.password
                DefaultFormattedErrorMapper.map(
                    validationResult = result,
                    onEmpty = { LoginFieldError.Password.Empty },
                    onLengthNotAcceptable = { LoginFieldError.Password.MaxLengthExceeded },
                    onIncorrectFormat = { LoginFieldError.Password.IncorrectFormat },
                )
            }
        }
    }

    private fun FormFieldState.setError(fieldError: FieldError?): FormFieldState {
        if (fieldError == null) return FormFieldState.Valid(
            isDisplayable = isDisplayable
        )

        return when (this) {
            is FormFieldState.Valid -> FormFieldState.ErrorState(
                isDisplayable = isDisplayable,
                fieldError = fieldError
            )
            is FormFieldState.ErrorState -> copy(fieldError = fieldError)
        }
    }
}
