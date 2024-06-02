package io.devexpert.kmpmovies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform