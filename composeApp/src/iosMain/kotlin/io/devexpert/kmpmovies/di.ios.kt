package io.devexpert.kmpmovies

import io.devexpert.kmpmovies.data.database.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder() }
}