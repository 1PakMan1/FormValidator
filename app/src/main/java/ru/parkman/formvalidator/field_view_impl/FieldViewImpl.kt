package ru.parkman.formvalidator.field_view_impl

import android.widget.TextView
import androidx.core.view.isInvisible
import ru.parkman.formvalidator.field_view.FieldView
import ru.parkman.formvalidator.OutlinedEditText

data class FieldViewImpl(
    private val input: OutlinedEditText,
    private val errorMessage: TextView
) : FieldView {

        override fun displayErrorMessage(show: Boolean, message: Int?) {
            input.errorState = show
            errorMessage.isInvisible = show.not()
            message?.let(errorMessage::setText)
        }
    }
