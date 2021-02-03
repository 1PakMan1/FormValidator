package ru.parkman.formvalidator.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

internal class Schedulers {
    companion object {
        fun ui(): Scheduler = AndroidSchedulers.mainThread()

        fun io(): Scheduler = Schedulers.io()
    }
}