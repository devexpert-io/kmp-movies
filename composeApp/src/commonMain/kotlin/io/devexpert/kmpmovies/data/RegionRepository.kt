package io.devexpert.kmpmovies.data

interface RegionDataSource {
    suspend fun fetchRegion(): String
}

class RegionRepository(private val regionDataSource: RegionDataSource) {

    suspend fun fetchRegion(): String {
        return regionDataSource.fetchRegion()
    }
}