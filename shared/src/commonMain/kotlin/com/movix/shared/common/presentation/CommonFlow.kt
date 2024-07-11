package com.movix.shared.common.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

expect open class CommonFlow<T>(
    flow: Flow<T>
) : Flow<T> {

    override suspend fun collect(collector: FlowCollector<T>)
}

fun <T> StateFlow<T>.toCommonFlow(): CommonFlow<T> = CommonFlow(this)
