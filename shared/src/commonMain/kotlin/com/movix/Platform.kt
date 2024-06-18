package com.movix

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform