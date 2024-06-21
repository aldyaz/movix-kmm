package com.movix.shared.common.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

expect open class CommonFlow<T>(
    flow: Flow<T>
) : Flow<T>

fun <T> StateFlow<T>.toCommonFlow(): CommonFlow<T> = CommonFlow(this)
