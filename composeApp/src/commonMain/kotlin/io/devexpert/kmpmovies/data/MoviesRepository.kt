package io.devexpert.kmpmovies.data

import io.devexpert.kmpmovies.data.database.MoviesDao
import io.devexpert.kmpmovies.data.remote.MoviesService
import io.devexpert.kmpmovies.data.remote.RemoteMovie
import kotlinx.coroutines.flow.onEach

class MoviesRepository(
    private val moviesService: MoviesService,
    private val moviesDao: MoviesDao,
    private val regionRepository: RegionRepository
) {

    val movies = moviesDao.fetchPopularMovies().onEach { movies ->
        if (movies.isEmpty()) {
            val remoteMovies = moviesService.fetchPopularMovies(regionRepository.fetchRegion())
                .results.map { it.toDomainMovie() }
            moviesDao.save(remoteMovies)
        }
    }

    fun fetchMovieById(id: Int) = moviesDao.findMovieById(id).onEach { movie ->
        if (movie == null) {
            val remoteMovie = moviesService.fetchMovieById(id).toDomainMovie()
            moviesDao.save(listOf(remoteMovie))
        }
    }

    suspend fun toggleFavorite(movie: Movie) {
        moviesDao.save(listOf(movie.copy(isFavorite = !movie.isFavorite)))
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
    voteAverage,
    false
)
