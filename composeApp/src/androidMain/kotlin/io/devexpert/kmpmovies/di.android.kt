package io.devexpert.kmpmovies

import io.devexpert.kmpmovies.data.AndroidRegionDataSource
import io.devexpert.kmpmovies.data.RegionDataSource
import io.devexpert.kmpmovies.data.database.getDatabaseBuilder
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder(get()) }
    factoryOf(::AndroidRegionDataSource) bind RegionDataSource::class
}