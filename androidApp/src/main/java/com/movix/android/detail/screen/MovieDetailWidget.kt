package com.movix.android.detail.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.movix.android.R
import com.movix.android.component.chip.BaseChip
import com.movix.shared.common.MovieImageApi

@Composable
fun DetailHeaderSection(
    title: String,
    releaseDate: String,
    rating: Float,
    posterPath: String,
    backdropPath: String,
    showTimeDuration: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = MovieImageApi.imageW780Url(backdropPath),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 8f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp
            )
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.height(140.dp)
            ) {
                AsyncImage(
                    model = MovieImageApi.imageW500Url(posterPath),
                    contentDescription = null,
                )
            }
            Column(
                modifier = Modifier.padding(
                    start = 12.dp
                )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextIcon(
                    icon = Icons.Filled.Star,
                    text = String.format("%.1f", rating),
                    iconTint = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextIcon(
                    icon = Icons.Filled.CalendarToday,
                    text = releaseDate
                )
                Spacer(modifier = Modifier.height(6.dp))
                TextIcon(
                    icon = Icons.Filled.AccessTime,
                    text = showTimeDuration
                )
            }
        }
    }
}

@Composable
fun DetailOverviewSection(
    overview: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.label_overview),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        ExpandedText(
            text = overview,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ExpandedText(
    text: String,
    modifier: Modifier = Modifier,
    expandable: Boolean = true,
    collapsedMaxLines: Int = 4,
    expandedMaxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTextStyle.current
) {
    var canTextExpandable by remember(text) {
        mutableStateOf(true)
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    Text(
        text = text,
        maxLines = if (expanded) expandedMaxLines else collapsedMaxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = {
            if (!expanded) {
                canTextExpandable = it.hasVisualOverflow
            }
        },
        style = style,
        modifier = Modifier
            .clickable(
                enabled = expandable && canTextExpandable,
                onClick = {
                    expanded = !expanded
                }
            )
            .then(modifier)
    )
}

@Composable
fun GenresHorizontalScrollable(
    genres: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(genres) { index, item ->
            BaseChip(
                category = item,
                modifier = Modifier.padding(
                    start = if (index == 0) 16.dp else 4.dp,
                    end = if (index == genres.size - 1) 16.dp else 4.dp
                )
            )
        }
    }
}

@Composable
private fun TextIcon(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    iconTint: Color = LocalContentColor.current
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = iconTint,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2
        )
    }
}

@Preview
@Composable
private fun TextIconPreview() {
    TextIcon(
        icon = Icons.Filled.CalendarToday,
        text = "January 2022"
    )
}
