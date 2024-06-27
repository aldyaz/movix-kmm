package com.movix.android.detail.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movix.android.component.error.BasicError
import com.movix.android.component.loading.BasicCircularLoading
import com.movix.android.component.observer.ScreenEnterObserver
import com.movix.android.detail.viewmodel.AndroidMovieDetailViewModel
import com.movix.shared.presentation.model.MovieDetailIntent
import com.movix.shared.presentation.model.MovieDetailState

@Composable
fun MovieDetailPage(
    id: Long
) {
    val viewModel: AndroidMovieDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScreenEnterObserver {
        viewModel.onDispatchIntent(MovieDetailIntent.OnEnter(id))
    }

    MovieDetailContent(
        uiState = uiState
    )
}

@Composable
private fun MovieDetailContent(
    uiState: MovieDetailState
) {
    Scaffold { innerPadding ->
        val modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()

        when {
            uiState.loading -> BasicCircularLoading(
                modifier = modifier
            )

            uiState.error -> BasicError(
                modifier = modifier,
                onRetryClick = {}
            )

            else -> {
                val data = uiState.data
                LazyColumn(
                    modifier = modifier,
                    content = {
                        item {
                            DetailHeaderSection(
                                title = data.title,
                                releaseDate = data.releaseDate,
                                rating = data.rating,
                                ratingStar = data.ratingStar,
                                posterPath = data.posterPath,
                                backdropPath = data.backdropPath,
                                showTimeDuration = data.duration
                            )
                        }

                        item {
                            GenresHorizontalScrollable(
                                genres = data.genres,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        vertical = 12.dp
                                    )
                            )
                        }

                        item {
                            DetailOverviewSection(
                                overview = data.overview,
                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                )
                            )
                        }
                    }
                )
            }
        }
    }
}
