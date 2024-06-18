package com.movix.shared.domain.base

class CloudApiException(
    val errorMessage: String
) : DomainException()