package ru.parkman.formvalidator.form_validation

import android.util.Patterns

abstract class Validator<Input, out Result : ValidationResult> {
    abstract fun validate(input: Input): Result
}

class EmailValidator(
    private val maxLength: Int = EMAIL_MAX_LENGTH
) : Validator<String, FormattedTextValidationResult>() {
    override fun validate(input: String): FormattedTextValidationResult {
        return FormattedTextValidationResult(
            input.isNotEmpty(),
            input.length < maxLength,
            Patterns.EMAIL_ADDRESS.matcher(input).matches()
        )
    }

    companion object {
        private const val EMAIL_MAX_LENGTH = 320
    }
}

class NotEmptyWithMaxLengthValidator(
    private val maxLength: Int = NAME_MAX_LENGTH
) : Validator<String, TextWithMaxLengthValidationResult>() {
    override fun validate(input: String): TextWithMaxLengthValidationResult {
        return TextWithMaxLengthValidationResult(
            input.isNotEmpty(),
            input.length < maxLength
        )
    }

    companion object {
        private const val NAME_MAX_LENGTH = 30
    }
}

class PasswordValidator(
    private val maxLength: Int = PASSWORD_MAX_LENGTH
) : Validator<String, FormattedTextValidationResult>() {
    override fun validate(input: String): FormattedTextValidationResult {
        return FormattedTextValidationResult(
            input.isNotEmpty(),
            input.length < maxLength,
            input.length >= PASSWORD_MIN_LENGTH
        )
    }

    companion object {
        private const val PASSWORD_MAX_LENGTH = 320
        private const val PASSWORD_MIN_LENGTH = 8
    }
}
