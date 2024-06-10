package io.devexpert.kmpmovies

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}