package ru.parkman.formvalidator.form

/**
 * Created by Igor Park on 24/09/2020.
 */
data class LoginForm(
    val email: String,
    val password: String
) : CommonForm {
    override fun isEmpty(): Boolean {
        return this == EMPTY
    }

    companion object {
        val EMPTY = LoginForm("", "")
    }
}