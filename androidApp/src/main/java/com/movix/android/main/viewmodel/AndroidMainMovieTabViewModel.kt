package com.movix.android.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movix.shared.domain.interactor.GetNowPlayingMoviesUseCase
import com.movix.shared.domain.interactor.GetPopularMoviesUseCase
import com.movix.shared.domain.interactor.GetTopRatedMoviesUseCase
import com.movix.shared.presentation.MainMovieTabViewModel
import com.movix.shared.presentation.mapper.MovieToPresentationMapper
import com.movix.shared.presentation.model.HomeTabViewIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidMainMovieTabViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val movieToPresentationMapper: MovieToPresentationMapper
) : ViewModel() {

    private val viewModel by lazy {
        MainMovieTabViewModel(
            getNowPlayingMoviesUseCase = getNowPlayingMoviesUseCase,
            getPopularMoviesUseCase = getPopularMoviesUseCase,
            getTopRatedMoviesUseCase = getTopRatedMoviesUseCase,
            movieToPresentationMapper = movieToPresentationMapper,
            coroutineScope = viewModelScope
        )
    }

    val uiState = viewModel.uiState

    fun onDispatchIntent(intent: HomeTabViewIntent) {
        viewModel.onDispatchIntent(intent)
    }
}