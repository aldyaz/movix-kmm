package com.movix.android.main.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.movix.android.component.toolbar.MainAppBar
import com.movix.android.main.model.MainTabType
import com.movix.android.main.viewmodel.MainViewModel

@Composable
fun MainPage(
    onNavigateToDetail: (Long) -> Unit
) {
    val viewModel: MainViewModel = hiltViewModel()

    val selectedTab by viewModel.selectedTab
    val tabs = enumValues<MainTabType>()

    Scaffold(
        topBar = {
            MainAppBar(
                onSearchClick = { /*TODO: Search screen*/ }
            )
        },
        bottomBar = {
            NavigationBar {
                tabs.forEach { type ->
                    NavigationBarItem(
                        selected = type == selectedTab,
                        onClick = {
                            viewModel.selectTab(type)
                        },
                        icon = {
                            Icon(
                                imageVector = type.icon,
                                contentDescription = stringResource(type.title)
                            )
                        },
                        label = {
                            Text(stringResource(type.title))
                        },
                        modifier = Modifier.navigationBarsPadding()
                    )
                }
            }
        }
    ) { innerPadding ->

        val modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()

        Crossfade(
            targetState = selectedTab,
            label = stringResource(selectedTab.title),
            content = { type ->
                when (type) {
                    MainTabType.MOVIE -> MovieDiscoverTab(
                        modifier = modifier,
                        onNavigateToDetail = onNavigateToDetail
                    )

                    MainTabType.TV_SHOW -> TvShowDiscoverTab(
                        modifier = modifier
                    )
                }
            }
        )
    }
}
