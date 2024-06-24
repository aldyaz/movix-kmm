package com.movix.android.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movix.shared.domain.interactor.GetMovieDetailUseCase
import com.movix.shared.presentation.mapper.MovieToPresentationMapper
import com.movix.shared.presentation.model.MovieDetailIntent
import com.movix.shared.presentation.viewmodel.MovieDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidMovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val movieToPresentationMapper: MovieToPresentationMapper
) : ViewModel() {

    private val viewModel by lazy {
        MovieDetailViewModel(
            movieDetailUseCase = movieDetailUseCase,
            movieToPresentationMapper = movieToPresentationMapper,
            coroutineScope = viewModelScope
        )
    }

    val uiState = viewModel.uiState

    fun onDispatchIntent(intent: MovieDetailIntent) {
        viewModel.onDispatchIntent(intent)
    }

}