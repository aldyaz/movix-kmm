package com.movix.shared.domain.base

abstract class DomainException(
    cause: Throwable? = null
) : Exception(cause)