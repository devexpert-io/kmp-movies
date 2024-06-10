package io.devexpert.kmpmovies

import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import io.devexpert.kmpmovies.data.AndroidRegionDataSource
import io.devexpert.kmpmovies.data.RegionDataSource
import io.devexpert.kmpmovies.data.database.getDatabaseBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder(get()) }
    factory { Geocoder(get()) }
    factory { LocationServices.getFusedLocationProviderClient(androidApplication()) }
    factoryOf(::AndroidRegionDataSource) bind RegionDataSource::class
}