package com.salihakbas.movieappcompose.data.model.series

data class SeriesCreditsResponse(
    val cast: List<SeriesCast> = emptyList(),
    val crew: List<SeriesCrew> = emptyList()
)