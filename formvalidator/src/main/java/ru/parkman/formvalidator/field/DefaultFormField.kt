package ru.parkman.formvalidator.field

import ru.parkman.formvalidator.field_view.FieldView

/**
 * Created by Igor Park on 16/09/2020.
 */
abstract class DefaultFormField : FormField {

    abstract val fieldView: FieldView

    override fun applyState(state: FormFieldState) {
        if (state is FormFieldState.ErrorState) {
            displayError(
                shouldShow = state.shouldShow,
                errorMessage = state.fieldError.messageRes
            )
        } else {
            hideError()
        }
    }

    private fun displayError(
        shouldShow: Boolean,
        errorMessage: Int
    ) {
        fieldView.displayErrorMessage(
            show = shouldShow,
            message = errorMessage
        )
    }

    private fun hideError() {
        fieldView.displayErrorMessage(show = false, message = null)
    }
}
