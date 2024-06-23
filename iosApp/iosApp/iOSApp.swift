import SwiftUI

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            BottomNavigation()
        }
    }
}

extension View {
    func centered() -> some View {
        self.frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(Color(.systemBackground))
    }
}
