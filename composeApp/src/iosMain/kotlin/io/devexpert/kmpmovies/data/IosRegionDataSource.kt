package io.devexpert.kmpmovies.data

import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.CLPlacemark
import kotlin.coroutines.resume

class IosRegionDataSource : RegionDataSource {

    private val locationManager = CLLocationManager()

    override suspend fun fetchRegion(): String {
        return getCurrentLocation()?.toRegion() ?: DEFAULT_REGION
    }

    private suspend fun getCurrentLocation(): CLLocation? {
        return suspendCancellableCoroutine { continuation ->
            locationManager.delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
                override fun locationManager(
                    manager: CLLocationManager,
                    didUpdateLocations: List<*>
                ) {
                    val location = didUpdateLocations.lastOrNull() as? CLLocation
                    locationManager.stopUpdatingLocation()
                    continuation.resume(location)
                }

                override fun locationManager(
                    manager: CLLocationManager,
                    didFailWithError: NSError
                ) {
                    continuation.resume(null)
                }
            }
            locationManager.requestWhenInUseAuthorization()
            locationManager.startUpdatingLocation()
        }
    }

    private suspend fun CLLocation.toRegion(): String {
        return suspendCancellableCoroutine { continuation ->
            val geocoder = CLGeocoder()
            geocoder.reverseGeocodeLocation(this) { placemarks, error ->
                if (error != null || placemarks == null) {
                    continuation.resume(DEFAULT_REGION)
                } else {
                    val countryCode = placemarks.firstOrNull()?.let { (it as CLPlacemark).ISOcountryCode }
                    continuation.resume(countryCode ?: DEFAULT_REGION)
                }
            }
        }
    }
}