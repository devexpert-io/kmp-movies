package io.devexpert.kmpmovies.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MoviesService(private val client: HttpClient) {

    suspend fun fetchPopularMovies(): RemoteResult =
        client.get("/discover/movie?sort_by=popularity.desc").body()

    suspend fun fetchMovieById(id: Int): RemoteMovie = client.get("/movie/$id").body()
}