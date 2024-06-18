package com.movix.shared.domain.base

class UnknownException(
    override val message: String = "Unknown exception!"
) : DomainException()