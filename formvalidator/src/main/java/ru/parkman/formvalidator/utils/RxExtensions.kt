package ru.parkman.formvalidator.utils

import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import java.util.concurrent.TimeUnit

/**
 * Created by Igor Park on 24/09/2020.
 */
internal typealias Rx2Observable<T> = io.reactivex.Observable<T>

internal fun <T> Observable<T>.toRx2Observable(): io.reactivex.Observable<T> {
    return RxJavaBridge.toV2Observable(this)
}

internal fun <T> Observable<T>.zipWithTimer(ms: Long): Observable<T> {
    return this.zipWith(
        Observable.timer(ms, TimeUnit.MILLISECONDS), BiFunction { t, _ -> t }
    )
}