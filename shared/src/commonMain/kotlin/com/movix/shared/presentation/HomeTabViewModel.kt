package com.movix.shared.presentation

import com.movix.shared.common.presentation.BaseViewModel
import com.movix.shared.common.presentation.toCommonStateFlow
import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.interactor.GetNowPlayingUseCase
import com.movix.shared.domain.interactor.GetPopularUseCase
import com.movix.shared.domain.interactor.GetTopRatedUseCase
import com.movix.shared.domain.model.MovieListDomainModel
import com.movix.shared.presentation.mapper.MovieToPresentationMapper
import com.movix.shared.presentation.model.DiscoverTypePresentationModel
import com.movix.shared.presentation.model.HomeTabViewIntent
import com.movix.shared.presentation.model.HomeTabViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class HomeTabViewModel(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getPopularUseCase: GetPopularUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val movieToPresentationMapper: MovieToPresentationMapper,
    coroutineScope: CoroutineScope?
) : BaseViewModel<HomeTabViewIntent>() {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _nowPlayingFlow: Flow<ResultState<MovieListDomainModel>> = flow {
        emit(getNowPlayingUseCase(Unit))
    }
    private val _popularFlow: Flow<ResultState<MovieListDomainModel>> = flow {
        emit(getPopularUseCase(Unit))
    }
    private val _topRatedFlow: Flow<ResultState<MovieListDomainModel>> = flow {
        emit(getTopRatedUseCase(Unit))
    }

    private val _state = MutableStateFlow(HomeTabViewState())
    val state = combine(
        _state,
        _nowPlayingFlow,
        _popularFlow,
        _topRatedFlow
    ) { state, nowPlaying, popular, topRated ->
        val results = mutableListOf<DiscoverTypePresentationModel>()
        if (nowPlaying is ResultState.Success) {
            results.add(
                DiscoverTypePresentationModel(
                    type = DiscoverTypePresentationModel.Type.NOW_PLAYING,
                    items = nowPlaying.data.movies.map(movieToPresentationMapper)
                )
            )
        }
        if (popular is ResultState.Success) {
            results.add(
                DiscoverTypePresentationModel(
                    type = DiscoverTypePresentationModel.Type.POPULAR,
                    items = popular.data.movies.map(movieToPresentationMapper)
                )
            )
        }
        if (topRated is ResultState.Success) {
            results.add(
                DiscoverTypePresentationModel(
                    type = DiscoverTypePresentationModel.Type.TOP_RATED,
                    items = topRated.data.movies.map(movieToPresentationMapper)
                )
            )
        }

        val isError = nowPlaying is ResultState.Error ||
                popular is ResultState.Error ||
                topRated is ResultState.Error

        state.copy(
            loading = false,
            error = isError,
            errorMessage = (nowPlaying as? ResultState.Error)?.exception?.message.orEmpty(),
            discoverTypeList = results
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = HomeTabViewState()
    ).toCommonStateFlow()

    override fun onDispatchIntent(intent: HomeTabViewIntent) {
        when (intent) {
            is HomeTabViewIntent.Retry -> {}
        }
    }
}