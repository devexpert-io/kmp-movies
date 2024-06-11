import Foundation
import CoreLocation

class LocationManager: NSObject, ObservableObject, CLLocationManagerDelegate {
    private let locationManager = CLLocationManager()
    private var callback : Optional<() -> Void> = nil
    
    @Published var authorizationStatus: CLAuthorizationStatus = .notDetermined
    
    override init() {
        super.init()
        self.locationManager.delegate = self
        self.authorizationStatus = locationManager.authorizationStatus
    }
    
    func requestPermission(completion: @escaping () -> Void) {
        if(authorizationStatus != .notDetermined){
            completion()
        } else {
            callback = completion
            locationManager.requestWhenInUseAuthorization()
        }
    }
    
    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        DispatchQueue.main.async {
            self.authorizationStatus = status
            if let callback = self.callback {
                if(self.authorizationStatus != .notDetermined){
                    callback()
                    self.callback = nil
                }
            }
        }
    }
}
