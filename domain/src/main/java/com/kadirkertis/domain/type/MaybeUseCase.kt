package com.kadirkertis.domain.type

import io.reactivex.Maybe

interface MaybeUseCase<T> {
    fun execute(): Maybe<T>
}