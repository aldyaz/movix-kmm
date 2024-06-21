package com.movix.shared.presentation

import com.movix.shared.common.presentation.BaseViewModel
import com.movix.shared.common.presentation.toCommonStateFlow
import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.interactor.GetNowPlayingUseCase
import com.movix.shared.domain.interactor.GetPopularUseCase
import com.movix.shared.domain.interactor.GetTopRatedUseCase
import com.movix.shared.presentation.mapper.MovieToPresentationMapper
import com.movix.shared.presentation.model.DiscoverMovieIntent
import com.movix.shared.presentation.model.DiscoverMovieViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DiscoverMovieViewModel(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getPopularUseCase: GetPopularUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val movieToPresentationMapper: MovieToPresentationMapper,
    coroutineScope: CoroutineScope?
) : BaseViewModel<DiscoverMovieIntent>() {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _nowPlaying = MutableStateFlow(DiscoverMovieViewState())
    val nowPlaying: StateFlow<DiscoverMovieViewState>
        get() = _nowPlaying
            .asStateFlow()
            .toCommonStateFlow()

    private val _popular = MutableStateFlow(DiscoverMovieViewState())
    val popular: StateFlow<DiscoverMovieViewState>
        get() = _popular
            .asStateFlow()
            .toCommonStateFlow()

    private val _topRated = MutableStateFlow(DiscoverMovieViewState())
    val topRated: StateFlow<DiscoverMovieViewState>
        get() = _topRated
            .asStateFlow()
            .toCommonStateFlow()

    override fun onDispatchIntent(intent: DiscoverMovieIntent) {
        when (intent) {
            is DiscoverMovieIntent.OnRefresh -> getMovies()
        }
    }

    private fun getMovies() = viewModelScope.launch {
        fetchNowPlaying()
        fetchPopular()
        fetchTopRated()
    }

    private fun fetchNowPlaying() = viewModelScope.launch {
        _nowPlaying.update { it.copy(loading = true) }
        when (val nowPlayingResult = getNowPlayingUseCase(Unit)) {
            is ResultState.Error -> {
                _nowPlaying.update {
                    it.copy(
                        loading = false,
                        error = true
                    )
                }
            }

            is ResultState.Success -> {
                _nowPlaying.update {
                    it.copy(
                        loading = false,
                        items = nowPlayingResult.data.movies.map(movieToPresentationMapper)
                    )
                }
            }
        }
    }

    private fun fetchPopular() = viewModelScope.launch {
        _popular.update { it.copy(loading = true) }
        when (val popularResult = getPopularUseCase(Unit)) {
            is ResultState.Error -> {
                _popular.update {
                    it.copy(
                        loading = false,
                        error = true
                    )
                }
            }

            is ResultState.Success -> {
                _popular.update {
                    it.copy(
                        loading = false,
                        items = popularResult.data.movies.map(movieToPresentationMapper)
                    )
                }
            }
        }
    }

    private fun fetchTopRated() = viewModelScope.launch {
        _topRated.update { it.copy(loading = true) }
        when (val topRatedResult = getTopRatedUseCase(Unit)) {
            is ResultState.Error -> {
                _topRated.update {
                    it.copy(
                        loading = false,
                        error = true
                    )
                }
            }

            is ResultState.Success -> {
                _topRated.update {
                    it.copy(
                        loading = false,
                        items = topRatedResult.data.movies.map(movieToPresentationMapper)
                    )
                }
            }
        }
    }
}