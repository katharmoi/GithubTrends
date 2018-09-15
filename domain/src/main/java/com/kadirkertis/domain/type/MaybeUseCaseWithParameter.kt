package com.kadirkertis.domain.type

import io.reactivex.Maybe

interface MaybeUseCaseWithParameter<T, P> {
    fun execute(param: P): Maybe<T>
}