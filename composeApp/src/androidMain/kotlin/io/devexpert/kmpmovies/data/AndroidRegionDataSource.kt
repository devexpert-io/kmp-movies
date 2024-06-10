package io.devexpert.kmpmovies.data

class AndroidRegionDataSource: RegionDataSource {
    override suspend fun fetchRegion(): String {
        return "US"
    }
}