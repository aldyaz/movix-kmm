package com.movix.shared.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

interface CoroutineContextProvider {

    val io: CoroutineDispatcher
    val main: CoroutineDispatcher

    object Default : CoroutineContextProvider {

        override val io: CoroutineDispatcher
            get() = Dispatchers.IO

        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
    }

}