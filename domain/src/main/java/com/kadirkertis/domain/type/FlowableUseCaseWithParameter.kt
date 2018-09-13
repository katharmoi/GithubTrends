package com.kadirkertis.domain.type

import io.reactivex.Flowable


interface FlowableUseCaseWithParameter<T, P> {
    fun execute(param: P): Flowable<T>
}
