package com.movix.shared.presentation.viewmodel

import com.movix.shared.common.presentation.BaseViewModel
import com.movix.shared.common.presentation.CommonStateFlow
import com.movix.shared.common.presentation.toCommonStateFlow
import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.interactor.GetMovieDetailUseCase
import com.movix.shared.presentation.mapper.MovieToPresentationMapper
import com.movix.shared.presentation.model.MovieDetailIntent
import com.movix.shared.presentation.model.MovieDetailState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val movieToPresentationMapper: MovieToPresentationMapper,
    coroutineScope: CoroutineScope?
) : BaseViewModel<MovieDetailIntent>() {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _uiState = MutableStateFlow(MovieDetailState())
    val uiState: CommonStateFlow<MovieDetailState>
        get() = _uiState.asStateFlow().toCommonStateFlow()

    override fun onDispatchIntent(intent: MovieDetailIntent) {
        when (intent) {
            is MovieDetailIntent.OnEnter -> getDetail(intent.movieId)
        }
    }

    private fun getDetail(movieId: Long) = viewModelScope.launch {
        _uiState.update {
            it.copy(
                loading = true,
                error = false
            )
        }
        when (val result = movieDetailUseCase(movieId)) {
            is ResultState.Success -> {
                _uiState.update {
                    it.copy(
                        loading = false,
                        data = movieToPresentationMapper(result.data)
                    )
                }
            }

            is ResultState.Error -> {
                _uiState.update {
                    it.copy(
                        loading = false,
                        error = true
                    )
                }
            }
        }
    }

}