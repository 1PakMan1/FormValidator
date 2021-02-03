package ru.parkman.formvalidator.form_validation_impl

import ru.parkman.formvalidator.form_validation.FormattedTextValidationResult
import ru.parkman.formvalidator.form_validation.ValidationResult

data class LoginFormValidationResult(
    val email: FormattedTextValidationResult,
    val password: FormattedTextValidationResult
) : ValidationResult {
    override val isValid: Boolean = email.isValid && password.isValid
}