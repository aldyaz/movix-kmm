package com.movix.shared.presentation.model

data class HomeTabViewState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val errorMessage: String = "",
    val discoverTypeList: List<DiscoverTypePresentationModel> = listOf()
)