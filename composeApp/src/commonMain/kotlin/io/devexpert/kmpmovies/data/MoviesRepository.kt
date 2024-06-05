package io.devexpert.kmpmovies.data

class MoviesRepository(private val moviesService: MoviesService) {

    suspend fun fetchPopularMovies(): List<Movie> {
        return moviesService.fetchPopularMovies().results.map { it.toDomainMovie() }
    }

    suspend fun fetchMovieById(id: Int): Movie {
        return moviesService.fetchMovieById(id).toDomainMovie()
    }
}

private fun RemoteMovie.toDomainMovie(): Movie = Movie(
    id = id,
    title = title,
    poster = "https://image.tmdb.org/t/p/w185/$posterPath"
)
