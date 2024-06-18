package com.movix.shared.domain.mapper

import com.movix.shared.domain.base.CloudApiException
import com.movix.shared.domain.base.DomainException
import com.movix.shared.domain.base.UnknownException
import com.movix.shared.exception.BadRequestException
import com.movix.shared.exception.HttpException
import com.movix.shared.exception.InternalServerException

class HttpExceptionToDomainMapper : (Exception) -> DomainException {

    override fun invoke(p1: Exception): DomainException {
        return when (p1) {
            is HttpException -> {
                p1.cause?.let {
                    when (it) {
                        is BadRequestException -> CloudApiException(it.message)
                        is InternalServerException -> CloudApiException(it.message)
                        else -> UnknownException()
                    }
                } ?: UnknownException()
            }

            else -> UnknownException()
        }
    }
}