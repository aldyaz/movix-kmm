package com.movix.android.home.mapper

import com.movix.android.home.model.DiscoverSectionUiModel
import com.movix.android.model.MovieUiModel
import com.movix.shared.presentation.model.DiscoverTypePresentationModel

class DiscoverTypeToUiMapper : (DiscoverTypePresentationModel) -> DiscoverSectionUiModel {

    override fun invoke(p1: DiscoverTypePresentationModel): DiscoverSectionUiModel {
        return DiscoverSectionUiModel(
            title = p1.type.title,
            items = p1.items.map { movie ->
                MovieUiModel(
                    id = movie.id,
                    title = movie.title,
                    posterPath = movie.posterPath
                )
            }
        )
    }
}