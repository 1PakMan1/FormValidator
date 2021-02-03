package ru.parkman.formvalidator.field_view

import androidx.annotation.StringRes

interface FieldView {
    fun displayErrorMessage(show: Boolean, @StringRes message: Int?)
}
