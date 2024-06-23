package com.movix.android.component.observer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ScreenEnterObserver(
    onEnterScreen: () -> Unit
) {
    var entered by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(entered) {
        if (!entered) {
            entered = true
            onEnterScreen()
        }
    }
}