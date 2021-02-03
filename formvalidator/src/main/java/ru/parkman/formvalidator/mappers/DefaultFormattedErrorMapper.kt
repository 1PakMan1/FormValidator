package ru.parkman.formvalidator.mappers

import ru.parkman.formvalidator.field.FieldError
import ru.parkman.formvalidator.form_validation.FormattedTextValidationResult

/**
 * Created by Igor Park on 25/05/2020.
 */
object DefaultFormattedErrorMapper {
    fun map(
        validationResult: FormattedTextValidationResult,
        onEmpty: () -> FieldError,
        onLengthNotAcceptable: () -> FieldError,
        onIncorrectFormat: () -> FieldError
    ): FieldError? {
        val formatError = validationResult.isFormatCorrect.not()

        val maxLengthError = validationResult.isLengthAcceptable.not()

        val emptyError = validationResult.isNotEmpty.not()

        return when {
            emptyError -> onEmpty()
            formatError -> onIncorrectFormat()
            maxLengthError -> onLengthNotAcceptable()
            validationResult.isValid -> null
            else -> error("Unreachable condition")
        }
    }
}

