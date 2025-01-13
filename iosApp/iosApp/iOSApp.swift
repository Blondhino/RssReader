import SwiftUI

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            GeometryReader{geo in
                ContentView().edgesIgnoringSafeArea(.all)
            }
        }
    }
}
