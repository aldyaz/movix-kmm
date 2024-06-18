package com.movix.shared.domain.base

sealed class ResultState<out T> {

    data class Success<out T>(
        val data: T
    ) : ResultState<T>()

    data class Error(
        val exception: DomainException
    ) : ResultState<Nothing>()

}