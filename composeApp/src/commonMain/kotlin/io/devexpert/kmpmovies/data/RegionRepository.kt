package io.devexpert.kmpmovies.data

const val DEFAULT_REGION = "US"

interface RegionDataSource {
    suspend fun fetchRegion(): String
}

class RegionRepository(private val regionDataSource: RegionDataSource) {

    suspend fun fetchRegion(): String {
        return regionDataSource.fetchRegion()
    }
}