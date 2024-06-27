package com.movix.shared.utils

import java.text.SimpleDateFormat
import java.util.Locale

actual class DateUtils {

    actual fun formatDate(
        date: String,
        pattern: String
    ): String {
        val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = dateTimeFormat.parse(date)
        val newDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return currentDate?.let { newDateFormat.format(it) } ?: date
    }

}