package com.movix.shared.data.base

import com.movix.shared.exception.HttpException

sealed class HttpResult<out T : Any> {

    data class Success<out T : Any>(
        val data: T
    ) : HttpResult<T>()

    data class Error(
        val exception: HttpException
    ) : HttpResult<Nothing>()

}