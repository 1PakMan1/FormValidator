package ru.parkman.formvalidator.field

/**
 * Created by Igor Park on 22/05/2020.
 */

sealed class FormFieldState {
    abstract val isDisplayable: Boolean

    abstract fun setDisplayable(isDisplayable: Boolean): FormFieldState

    data class ErrorState(
        override val isDisplayable: Boolean,
        val fieldError: FieldError
    ) : FormFieldState() {
        val shouldShow: Boolean
            get() = isDisplayable

        override fun setDisplayable(isDisplayable: Boolean): FormFieldState {
            return copy(isDisplayable = isDisplayable)
        }
    }

    data class Valid(override val isDisplayable: Boolean) : FormFieldState() {
        override fun setDisplayable(isDisplayable: Boolean): FormFieldState {
            return copy(isDisplayable = isDisplayable)
        }
    }
}
