package com.movix.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform