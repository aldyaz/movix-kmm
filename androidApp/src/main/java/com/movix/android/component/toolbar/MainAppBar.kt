@file:OptIn(ExperimentalMaterial3Api::class)

package com.movix.android.component.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.movix.android.R

@Composable
fun MainAppBar(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = NavigationBarDefaults.containerColor
    TopAppBar(
        title = {
            Text(stringResource(R.string.label_app_name))
        },
        modifier = modifier,
        actions = {
            IconButton(
                onClick = onSearchClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.label_search)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = containerColor,
            navigationIconContentColor = MaterialTheme.colorScheme.contentColorFor(
                containerColor
            )
        )
    )
}