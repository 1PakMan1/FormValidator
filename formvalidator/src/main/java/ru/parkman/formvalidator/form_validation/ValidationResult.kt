package ru.parkman.formvalidator.form_validation

interface ValidationResult {
    val isValid: Boolean
}

class FormattedTextValidationResult(
    override val isNotEmpty: Boolean,
    override val isLengthAcceptable: Boolean,
    val isFormatCorrect: Boolean
) : ValidationResult, EmptyCheck, MaxLengthCheck {
    override val isValid: Boolean = isNotEmpty && isLengthAcceptable && isFormatCorrect
}

class TextWithMaxLengthValidationResult(
    override val isNotEmpty: Boolean,
    override val isLengthAcceptable: Boolean
) : ValidationResult, EmptyCheck, MaxLengthCheck {
    override val isValid: Boolean = isNotEmpty && isLengthAcceptable
}

class ConfirmPasswordValidationResult(
    override val isNotEmpty: Boolean,
    val passwordConfirmed: Boolean
) : ValidationResult, EmptyCheck {
    override val isValid: Boolean = isNotEmpty && passwordConfirmed
}

interface EmptyCheck {
    val isNotEmpty: Boolean
}

interface MaxLengthCheck {
    val isLengthAcceptable: Boolean
}
