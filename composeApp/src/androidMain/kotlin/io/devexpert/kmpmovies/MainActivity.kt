package io.devexpert.kmpmovies

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import io.devexpert.kmpmovies.data.database.getDatabaseBuilder
import io.devexpert.kmpmovies.data.database.getRoomDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EnableTransparentStatusBar()
            val db = getRoomDatabase(getDatabaseBuilder(ctx = LocalView.current.context))
            App(db.moviesDao())
        }
    }
}

@Composable
private fun EnableTransparentStatusBar() {
    val view = LocalView.current
    val darkTheme = isSystemInDarkTheme()
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                !darkTheme
        }
    }
}