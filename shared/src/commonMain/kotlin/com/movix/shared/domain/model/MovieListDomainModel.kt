package com.movix.shared.domain.model

data class MovieListDomainModel(
    val page: Int,
    val movies: List<MovieDomainModel>
)