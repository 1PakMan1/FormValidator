package ru.parkman.formvalidator.field

import ru.parkman.formvalidator.Qualifier
import ru.parkman.formvalidator.field.FormFieldState

/**
 * Created by Igor Park on 22/05/2020.
 */
interface FormField {
    val qualifier: Qualifier
    fun applyState(state: FormFieldState)
}
