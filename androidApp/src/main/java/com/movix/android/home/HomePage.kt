package com.movix.android.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
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
import com.movix.android.home.mapper.MovieToUiMapper
import com.movix.shared.presentation.model.DiscoverMovieState
import com.movix.shared.presentation.model.HomeTabViewIntent
import com.movix.shared.presentation.model.HomeTabViewState

@Composable
fun HomePage() {
    val viewModel: AndroidHomeTabViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ScreenEnterObserver {
        viewModel.onDispatchIntent(HomeTabViewIntent.OnEnter)
    }
    HomeContent(uiState = uiState)
}

@Composable
fun HomeContent(
    uiState: HomeTabViewState
) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                HomeDiscoverSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.label_now_playing),
                    state = uiState.nowPlaying,
                    onClickMore = {},
                    onClickItem = {}
                )
            }
            item {
                HomeDiscoverSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.label_popular),
                    state = uiState.popular,
                    onClickMore = {},
                    onClickItem = {}
                )
            }
            item {
                HomeDiscoverSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.label_top_rated),
                    state = uiState.topRated,
                    onClickMore = {},
                    onClickItem = {}
                )
            }
        }
    }
}

@Composable
fun HomeDiscoverSection(
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
