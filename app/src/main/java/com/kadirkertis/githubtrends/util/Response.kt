package com.kadirkertis.githubtrends.util

class Response<T> private constructor(val status: Status, val data: T?, val error: Throwable?) {

    companion object {

        fun <T> loading(): Response<T> {
            return Response(Status.LOADING, null, null)
        }

        fun <T> success(data: T): Response<T> {
            return Response(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable): Response<T> {
            return Response(Status.ERROR, null, error)
        }
    }
}