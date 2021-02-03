package ru.parkman.formvalidator.form

import ru.parkman.formvalidator.FormState
import ru.parkman.formvalidator.field.FormField

/**
 * Created by Igor Park on 2019-09-27.
 */
class FormFieldsHolder<Field : FormField>(
    private val fields: List<Field>
) {
    fun applyState(formState: FormState) {
        if (formState.isEmpty()) return

        fields.forEach { formField ->
            val fieldState = formState[formField.qualifier]
                ?: error("FormState does not have field: $formField")

            formField.applyState(fieldState)
        }
    }
}
