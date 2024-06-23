package com.movix.android.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.movix.android.model.MovieUiModel
import com.movix.shared.common.MovieImageApi

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    title: String,
    onClickMore: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    onClickMore()
                },
            contentDescription = null
        )
    }
}

@Composable
fun MovieRowList(
    modifier: Modifier = Modifier,
    items: List<MovieUiModel>,
    onClickItem: (Long) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(
            items = items,
            key = { _, item -> item.id }
        ) { index, item ->
            MoviePoster(
                imageUrl = MovieImageApi.imageW500Url(item.posterPath),
                contentDescription = item.title,
                modifier = Modifier
                    .aspectRatio(2 / 3f)
                    .padding(
                        start = index.getLeftSpaceMargin(),
                        end = index.getRightSpaceMargin(items.size)
                    ),
                onClick = {
                    onClickItem(item.id)
                }
            )
        }
    }
}

@Composable
fun MoviePoster(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick?.invoke() }
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

private fun Int.getLeftSpaceMargin() = if (this == 0) 12.dp else 2.dp

private fun Int.getRightSpaceMargin(
    endIndex: Int
) = if (this == endIndex - 1) 12.dp else 2.dp
