package io.devexpert.kmpmovies.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.devexpert.kmpmovies.data.MoviesService
import io.devexpert.kmpmovies.data.movies
import io.devexpert.kmpmovies.ui.screens.home.DetailScreen
import io.devexpert.kmpmovies.ui.screens.home.HomeScreen
import io.devexpert.kmpmovies.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.api_key
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val client = remember {
                HttpClient {
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                        })
                    }
                }
            }
            val apiKey = stringResource(Res.string.api_key)
            val homeViewModel = viewModel {
                HomeViewModel(MoviesService(apiKey, client))
            }

            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate("detail/${movie.id}")
                },
                vm = homeViewModel
            )
        }
        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            DetailScreen(
                movie = movies.first { it.id == movieId },
                onBack = { navController.popBackStack() })
        }
    }
}