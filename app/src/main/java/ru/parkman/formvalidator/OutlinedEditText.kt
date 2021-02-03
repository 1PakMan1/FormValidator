package ru.parkman.formvalidator

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class OutlinedEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    private val ERROR_STATE = intArrayOf(R.attr.state_error)

    var errorState: Boolean = false
        set(value) {
            field = value
            refreshDrawableState()
        }

    override fun onCreateDrawableState(extraSpace: Int): IntArray? {
        val state = super.onCreateDrawableState(extraSpace + 1)
        if (errorState) mergeDrawableStates(state, ERROR_STATE)

        return state
    }
}