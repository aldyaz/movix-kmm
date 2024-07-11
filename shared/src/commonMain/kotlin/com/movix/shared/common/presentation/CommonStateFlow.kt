package com.movix.shared.common.presentation

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

expect class CommonStateFlow<T>(
    flow: StateFlow<T>
) : StateFlow<T> {

    override val replayCache: List<T>

    override val value: T

    override suspend fun collect(collector: FlowCollector<T>): Nothing
}

fun <T> StateFlow<T>.toCommonStateFlow(): CommonStateFlow<T> = CommonStateFlow(this)
