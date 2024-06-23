package com.movix.android.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movix.android.component.error.BasicError
import com.movix.android.component.loading.BasicCircularLoading
import com.movix.android.home.mapper.DiscoverTypeToUiMapper
import com.movix.android.home.model.DiscoverSectionUiModel
import com.movix.shared.presentation.model.HomeTabViewState

@Composable
fun HomePage() {
    val viewModel: AndroidHomeViewModel = hiltViewModel()
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    HomeContent(uiState = uiState)
}

@Composable
fun HomeContent(
    uiState: HomeTabViewState
) {
    val context = LocalContext.current
    val discoverTypeToUiMapper = remember {
        DiscoverTypeToUiMapper()
    }
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            when {
                uiState.loading -> item {
                    BasicCircularLoading()
                }

                uiState.error -> item {
                    BasicError {
                    }
                }

                else -> {
                    items(uiState.discoverTypeList.map(discoverTypeToUiMapper)) { section ->
                        HomeDiscoverSection(
                            modifier = Modifier.fillMaxWidth(),
                            section = section
                        )
                    }
                }
            }
        }
    }

    if (uiState.errorMessage.isNotEmpty()) {
        Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun HomeDiscoverSection(
    modifier: Modifier = Modifier,
    section: DiscoverSectionUiModel
) {
    Column(
        modifier = modifier,
        content = {
            SectionHeader(
                title = section.title,
                onClickMore = {}
            )
            MovieRowList(
                items = section.items,
                onClickItem = { movieId ->
                }
            )
        }
    )
}
