package com.movix.android.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.movix.android.main.model.MainTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _selectedTab: MutableState<MainTabType> = mutableStateOf(MainTabType.MOVIE)
    val selectedTab: State<MainTabType>
        get() = _selectedTab

    fun selectTab(type: MainTabType) {
        _selectedTab.value = type
    }

}