package io.devexpert.kmpmovies.data

import io.devexpert.kmpmovies.data.database.MoviesDao
import kotlinx.coroutines.flow.onEach

class MoviesRepository(private val moviesService: MoviesService, private val moviesDao: MoviesDao) {

    val movies = moviesDao.fetchPopularMovies().onEach { movies ->
        if (movies.isEmpty()) {
            val remoteMovies = moviesService.fetchPopularMovies().results.map { it.toDomainMovie() }
            moviesDao.save(remoteMovies)
        }
    }

    fun fetchMovieById(id: Int) = moviesDao.findMovieById(id).onEach { movie ->
        if (movie == null) {
            val remoteMovie = moviesService.fetchMovieById(id).toDomainMovie()
            moviesDao.save(listOf(remoteMovie))
        }
    }

}

private fun RemoteMovie.toDomainMovie() = Movie(
    id,
    title,
    overview,
    releaseDate,
    "https://image.tmdb.org/t/p/w185/$posterPath",
    backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" },
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage
)
