package io.devexpert.kmpmovies.data

class RegionRepository {

    suspend fun fetchRegion(): String {
        return "US"
    }
}