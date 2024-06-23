package com.movix.android.model

import androidx.compose.runtime.Immutable

@Immutable
data class MovieUiModel(
    val id: Long,
    val title: String,
    val posterPath: String
)