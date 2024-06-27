package com.movix.shared.utils

expect class DateUtils {

    fun formatDate(
        date: String,
        pattern: String
    ): String

}