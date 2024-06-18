package com.movix.shared.exception

class BadRequestException(
    override val message: String
) : Throwable()