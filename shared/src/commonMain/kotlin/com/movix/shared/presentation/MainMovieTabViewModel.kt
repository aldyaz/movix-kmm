package com.movix.shared.presentation

import com.movix.shared.common.presentation.BaseViewModel
import com.movix.shared.common.presentation.toCommonStateFlow
import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.interactor.GetNowPlayingUseCase
import com.movix.shared.domain.interactor.GetPopularUseCase
import com.movix.shared.domain.interactor.GetTopRatedUseCase
import com.movix.shared.presentation.mapper.MovieToPresentationMapper
import com.movix.shared.presentation.model.DiscoverMovieState
import com.movix.shared.presentation.model.HomeTabViewIntent
import com.movix.shared.presentation.model.HomeTabViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainMovieTabViewModel(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getPopularUseCase: GetPopularUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val movieToPresentationMapper: MovieToPresentationMapper,
    coroutineScope: CoroutineScope?
) : BaseViewModel<HomeTabViewIntent>() {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _nowPlayingState = MutableStateFlow(DiscoverMovieState.Initial)
    private val _popularState = MutableStateFlow(DiscoverMovieState.Initial)
    private val _topRatedState = MutableStateFlow(DiscoverMovieState.Initial)

    private val _uiState = MutableStateFlow(HomeTabViewState())
    val uiState = combine(
        _uiState,
        _nowPlayingState,
        _popularState,
        _topRatedState
    ) { state, nowPlaying, popular, topRated ->
        state.copy(
            nowPlaying = nowPlaying,
            popular = popular,
            topRated = topRated
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = HomeTabViewState()
    ).toCommonStateFlow()

    override fun onDispatchIntent(intent: HomeTabViewIntent) {
        when (intent) {
            is HomeTabViewIntent.OnEnter -> {
                getNowPlaying()
                getPopular()
                getTopRated()
            }
        }
    }

    private fun getNowPlaying() = viewModelScope.launch {
        _nowPlayingState.update {
            it.copy(
                loading = true,
                error = false
            )
        }
        when (val result = getNowPlayingUseCase(Unit)) {
            is ResultState.Success -> {
                _nowPlayingState.update {
                    it.copy(
                        loading = false,
                        items = result.data.movies.map(movieToPresentationMapper)
                    )
                }
            }

            is ResultState.Error -> {
                _nowPlayingState.update {
                    it.copy(
                        loading = false,
                        error = true
                    )
                }
            }
        }
    }

    private fun getPopular() = viewModelScope.launch {
        _popularState.update {
            it.copy(
                loading = true,
                error = false
            )
        }
        when (val result = getPopularUseCase(Unit)) {
            is ResultState.Success -> {
                _popularState.update {
                    it.copy(
                        loading = false,
                        items = result.data.movies.map(movieToPresentationMapper)
                    )
                }
            }

            is ResultState.Error -> {
                _popularState.update {
                    it.copy(
                        loading = false,
                        error = true
                    )
                }
            }
        }
    }

    private fun getTopRated() = viewModelScope.launch {
        _topRatedState.update {
            it.copy(
                loading = true,
                error = false
            )
        }
        when (val result = getTopRatedUseCase(Unit)) {
            is ResultState.Success -> {
                _topRatedState.update {
                    it.copy(
                        loading = false,
                        items = result.data.movies.map(movieToPresentationMapper)
                    )
                }
            }

            is ResultState.Error -> {
                _topRatedState.update {
                    it.copy(
                        loading = false,
                        error = true
                    )
                }
            }
        }
    }
}