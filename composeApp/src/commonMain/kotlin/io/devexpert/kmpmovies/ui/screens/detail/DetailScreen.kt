package io.devexpert.kmpmovies.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.devexpert.kmpmovies.data.Movie
import io.devexpert.kmpmovies.ui.common.LoadingIndicator
import io.devexpert.kmpmovies.ui.screens.Screen
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.back
import kmpmovies.composeapp.generated.resources.favorite
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit) {

    val state = vm.state
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Screen {
        Scaffold(
            topBar = {
                DetailTopBar(
                    title = state.movie?.title ?: "",
                    scrollBehavior = scrollBehavior,
                    onBack = onBack
                )
            },
            floatingActionButton = {
                state.movie?.let { movie ->
                    FloatingActionButton(onClick = vm::onFavoriteClick) {
                        Icon(
                            imageVector = if (movie.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(Res.string.favorite)
                        )
                    }
                }
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->

            LoadingIndicator(
                enabled = state.loading,
                modifier = Modifier.fillMaxSize().padding(padding)
            )

            state.movie?.let {
                MovieDetail(
                    movie = it,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
    title: String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(Res.string.back)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun MovieDetail(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = movie.backdrop,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )
        Text(text = movie.overview, modifier = Modifier.padding(16.dp))
        Text(
            text = buildAnnotatedString {
                Property("Original language", movie.originalLanguage)
                Property("Original title", movie.originalTitle)
                Property("Release date", movie.releaseDate)
                Property("Popularity", movie.popularity.toString())
                Property("Vote average", movie.voteAverage.toString(), true)
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp)
        )
    }
}

@Composable
private fun AnnotatedString.Builder.Property(name: String, value: String, end: Boolean = false) {
    withStyle(ParagraphStyle(lineHeight = 18.sp)) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$name: ")
        }
        append(value)
        if (!end) {
            append("\n")
        }
    }
}