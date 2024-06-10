package io.devexpert.kmpmovies

import androidx.compose.runtime.Composable
import io.devexpert.kmpmovies.ui.screens.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        Navigation()
    }
}