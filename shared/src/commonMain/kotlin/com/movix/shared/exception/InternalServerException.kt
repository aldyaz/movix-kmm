package com.movix.shared.exception

import io.ktor.utils.io.errors.IOException

class InternalServerException(
    override val message: String
) : Throwable()