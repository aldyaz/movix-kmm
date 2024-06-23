package com.movix.shared.presentation.model

sealed class HomeTabViewIntent {

    data object OnEnter : HomeTabViewIntent()

}