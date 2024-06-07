package io.devexpert.kmpmovies

import androidx.compose.ui.window.ComposeUIViewController
import io.devexpert.kmpmovies.data.database.MoviesDatabase
import io.devexpert.kmpmovies.data.database.getDatabaseBuilder
import io.devexpert.kmpmovies.data.database.getRoomDatabase

fun MainViewController() = ComposeUIViewController {
    val database: MoviesDatabase = getRoomDatabase(getDatabaseBuilder())
    App(database.moviesDao())
}