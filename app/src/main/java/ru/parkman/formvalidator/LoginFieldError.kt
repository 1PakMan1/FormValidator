package ru.parkman.formvalidator

import ru.parkman.formvalidator.field.FieldError

/**
 * Created by Igor Park on 25/09/2020.
 */
sealed class LoginFieldError: FieldError {
    sealed class Email : LoginFieldError() {

        object Empty : Email() {
            override val messageRes: Int = R.string.form_field_error_empty
        }

        object MaxLengthExceeded : Email() {
            override val messageRes: Int = R.string.form_field_error_name_max_length
        }

        object IncorrectFormat : Email() {
            override val messageRes: Int = R.string.form_field_error_email_format
        }
    }

    sealed class Password : LoginFieldError() {
        object Empty : Password() {
            override val messageRes: Int = R.string.form_field_error_empty
        }

        object MaxLengthExceeded : Password() {
            override val messageRes: Int = R.string.form_field_error_password_max_length
        }

        object IncorrectFormat : Password() {
            override val messageRes: Int = R.string.form_field_error_password_min_length
        }
    }
}