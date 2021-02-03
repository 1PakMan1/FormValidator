package ru.parkman.formvalidator.utils

import android.widget.TextView
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.core.Observable

/**
 * Created by Igor Park on 24/09/2020.
 */
fun TextView.textChanges(): Observable<String> {
    return this.textChanges()
        .map { it.toString() }
}
