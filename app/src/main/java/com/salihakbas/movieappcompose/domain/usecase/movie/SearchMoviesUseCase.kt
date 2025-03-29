package com.salihakbas.movieappcompose.domain.usecase.movie

import com.salihakbas.movieappcompose.common.Resource
import com.salihakbas.movieappcompose.data.model.Movie
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(query: String): Resource<List<Movie>> {
        return try {
            val response = repository.getSearchMovies(query)
            if (response.isNotEmpty()) {
                Resource.Success(response)
            } else {
                Resource.Error("No movies found!")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}