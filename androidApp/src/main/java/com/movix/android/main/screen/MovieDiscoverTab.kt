package com.movix.android.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movix.android.R
import com.movix.android.component.error.BasicError
import com.movix.android.component.loading.BasicCircularLoading
import com.movix.android.component.observer.ScreenEnterObserver
import com.movix.android.main.mapper.MovieToUiMapper
import com.movix.android.main.viewmodel.AndroidMainMovieTabViewModel
import com.movix.shared.presentation.model.DiscoverMovieState
import com.movix.shared.presentation.model.HomeTabViewIntent
import com.movix.shared.presentation.model.HomeTabViewState

@Composable
fun MovieDiscoverTab(
    modifier: Modifier = Modifier
) {
    val viewModel: AndroidMainMovieTabViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ScreenEnterObserver {
        viewModel.onDispatchIntent(HomeTabViewIntent.OnEnter)
    }
    MovieDiscoverTabContent(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun MovieDiscoverTabContent(
    modifier: Modifier = Modifier,
    uiState: HomeTabViewState
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            DiscoverSection(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.label_now_playing),
                state = uiState.nowPlaying,
                onClickMore = {},
                onClickItem = {}
            )
        }
        item {
            DiscoverSection(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.label_popular),
                state = uiState.popular,
                onClickMore = {},
                onClickItem = {}
            )
        }
        item {
            DiscoverSection(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.label_top_rated),
                state = uiState.topRated,
                onClickMore = {},
                onClickItem = {}
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DiscoverSection(
    modifier: Modifier = Modifier,
    title: String,
    state: DiscoverMovieState,
    onClickMore: () -> Unit,
    onClickItem: (Long) -> Unit
) {
    val movieToUiMapper = remember { MovieToUiMapper() }
    Column(modifier = modifier.fillMaxWidth()) {
        SectionHeader(
            title = title,
            onClickMore = onClickMore
        )
        when {
            state.loading -> BasicCircularLoading(
                modifier = Modifier.aspectRatio(4 / 3f)
            )

            state.error -> BasicError(
                modifier = Modifier.aspectRatio(4 / 3f),
                onRetryClick = {}
            )

            else -> MovieRowList(
                items = state.items.map(movieToUiMapper),
                onClickItem = onClickItem
            )
        }
    }
}
