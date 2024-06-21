package com.movix.shared.common.presentation

abstract class BaseViewModel<in INTENT> {

    abstract fun onDispatchIntent(intent: INTENT)

}