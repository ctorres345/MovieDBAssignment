package com.backbase.domain.model

sealed class Try<out T> {

    /**
     * Represents the possible failure causes.
     */
    sealed class FailureCause(val exception: Throwable) {
        data class Unknown(val error: Throwable) : FailureCause(error)
    }

    /**
     * Represents a successful call and the [value] that was requested.
     */
    data class Success<out T>(val value: T) : Try<T>() {
        override fun getOrNull(): T? = value
    }

    /**
     * Represents the failure case and the [cause] of it.
     */
    data class Failure<out T>(val cause: FailureCause) : Try<T>() {
        override fun getOrNull(): T? = null
    }

    /**
     * returns value if a [Success] [Try] or returns null
     */
    abstract fun getOrNull(): T?
}