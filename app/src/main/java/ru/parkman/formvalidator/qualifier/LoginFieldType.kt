package ru.parkman.formvalidator.qualifier

/**
 * Created by Igor Park on 2019-09-26.
 */

sealed class LoginFieldType {
    object Email : LoginFieldType()
    object Password : LoginFieldType()
}
