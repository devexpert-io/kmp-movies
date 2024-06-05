package io.devexpert.kmpmovies.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.devexpert.kmpmovies.data.MoviesRepository
import io.devexpert.kmpmovies.data.MoviesService
import io.devexpert.kmpmovies.ui.screens.detail.DetailScreen
import io.devexpert.kmpmovies.ui.screens.detail.DetailViewModel
import io.devexpert.kmpmovies.ui.screens.home.HomeScreen
import io.devexpert.kmpmovies.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.api_key
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val moviesRepository = rememberMoviesRepository()

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(
                onMovieClick = { navController.navigate("detail/${it.id}") },
                vm = viewModel { HomeViewModel(moviesRepository) }
            )
        }

        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = checkNotNull(backStackEntry.arguments?.getInt("movieId"))
            DetailScreen(
                vm = viewModel { DetailViewModel(movieId, moviesRepository) },
                onBack = { navController.popBackStack() })
        }
    }
}

@Composable
private fun rememberMoviesRepository(): MoviesRepository {
    val apiKey = stringResource(Res.string.api_key)

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.themoviedb.org/3"
                parameters.append("api_key", apiKey)
            }
        }
    }

    val moviesRepository = MoviesRepository(MoviesService(client))
    return remember { moviesRepository }
}