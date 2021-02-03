package ru.parkman.formvalidator.field_impl

import ru.parkman.formvalidator.qualifier.LoginFieldType
import ru.parkman.formvalidator.field.DefaultFormField
import ru.parkman.formvalidator.field_view.FieldView

/**
 * Created by Igor Park on 2019-09-27.
 */

sealed class LoginField : DefaultFormField() {
    data class Email(
        override val fieldView: FieldView
    ) : LoginField() {
        override val qualifier: LoginFieldType = LoginFieldType.Email
    }

    data class Password(
        override val fieldView: FieldView
    ) : LoginField() {
        override val qualifier: LoginFieldType = LoginFieldType.Password
    }
}
