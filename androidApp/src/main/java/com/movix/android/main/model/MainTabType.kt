package com.movix.android.main.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.movix.android.R

enum class MainTabType(
    @StringRes
    val title: Int,
    val icon: ImageVector
) {

    MOVIE(R.string.label_movie, Icons.Filled.Movie),
    TV_SHOW(R.string.label_tv_shows, Icons.Filled.Tv)

}