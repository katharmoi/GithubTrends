package com.kadirkertis.domain.type

import io.reactivex.Flowable


interface FlowableUseCase<T> {
    fun execute(): Flowable<T>
}
