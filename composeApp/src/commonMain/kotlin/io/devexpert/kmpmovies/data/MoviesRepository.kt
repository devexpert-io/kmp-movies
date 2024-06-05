package io.devexpert.kmpmovies.data

class MoviesRepository(private val moviesService: MoviesService) {

    suspend fun fetchPopularMovies(): RemoteResult {
        return moviesService.fetchPopularMovies()
    }
}