import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        DiKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			HomeScreen()
		}
	}
}
