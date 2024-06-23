package com.movix.android.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movix.shared.domain.interactor.GetNowPlayingUseCase
import com.movix.shared.domain.interactor.GetPopularUseCase
import com.movix.shared.domain.interactor.GetTopRatedUseCase
import com.movix.shared.presentation.HomeTabViewModel
import com.movix.shared.presentation.mapper.MovieToPresentationMapper
import com.movix.shared.presentation.model.HomeTabViewIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidHomeTabViewModel @Inject constructor(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getPopularUseCase: GetPopularUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val movieToPresentationMapper: MovieToPresentationMapper
) : ViewModel() {

    private val viewModel by lazy {
        HomeTabViewModel(
            getNowPlayingUseCase = getNowPlayingUseCase,
            getPopularUseCase = getPopularUseCase,
            getTopRatedUseCase = getTopRatedUseCase,
            movieToPresentationMapper = movieToPresentationMapper,
            coroutineScope = viewModelScope
        )
    }

    val uiState = viewModel.uiState

    fun onDispatchIntent(intent: HomeTabViewIntent) {
        viewModel.onDispatchIntent(intent)
    }
}