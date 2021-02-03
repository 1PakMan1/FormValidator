package ru.parkman.formvalidator.form_validation_impl

import ru.parkman.formvalidator.form.LoginForm
import ru.parkman.formvalidator.form_validation.EmailValidator
import ru.parkman.formvalidator.form_validation.PasswordValidator
import ru.parkman.formvalidator.form_validation.Validator

class LoginFormValidator : Validator<LoginForm, LoginFormValidationResult>() {
    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()

    override fun validate(input: LoginForm): LoginFormValidationResult = with(input) {
        LoginFormValidationResult(
            emailValidator.validate(email),
            passwordValidator.validate(password)
        )
    }
}