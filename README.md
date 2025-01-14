
# RssReader

RssReader is a Kotlin Multiplatform (KMP) application designed to help users manage and view content from their favorite RSS feeds. The app supports both Android and iOS platforms and uses modern technologies to provide a seamless experience, including offline-first functionality for data caching and viewing without mobile data.

---

## Features

- **Add and Manage RSS Feeds**  
  Add links to RSS feeds and organize them easily.

- **Favorite and Delete Feeds**  
  Mark feeds as favorites or delete them as needed.

- **Offline-First Approach**  
  All data is cached locally, allowing the app to function without an internet connection.

- **Two Main Screens**:
  - **Subscriptions Screen**: Displays all added RSS feeds, including favorited feeds.
  - **Articles Screen**: Displays the content of a selected RSS feed.

---

## Supported Platforms

- **Android**
- **iOS**

---

## Technologies Used

- **Kotlin Multiplatform**  
  Shared logic across all supported platforms, including the UI layer.

- **Compose Multiplatform**  
  Declarative UI framework for building modern and responsive UIs.

- **SQLDelight**  
  Database solution for local data storage and caching.

- **Ktor**  
  Networking library for fetching RSS feed data.

- **Koin**  
  Dependency injection framework for managing application dependencies.

- **Voyager**  
  Navigation library for handling screen transitions and routing.

- **Arrow's Either**  
  Used for functional error handling, ensuring cleaner and more predictable code.

---

## Architecture and Design

- **Offline-First**:  
  The app prioritizes showing cached data before fetching fresh data from the network, ensuring a smooth user experience even without an active internet connection.

- **Functional Programming**:  
  Leveraging Arrow's `Either` for robust error handling, making the app reliable and resilient.

- **Modern Multiplatform UI**:  
  The entire UI is built using Compose Multiplatform, ensuring consistency and maintainability across Android and iOS.

---

## How to Run

### Prerequisites
- Kotlin 1.9 or later
- Android Studio (for Android builds)
- Xcode (for iOS builds)
- Gradle 8.0 or later

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/rssreader.git
   cd rssreader
   ```
2. Open the project in Android Studio or your preferred IDE.
3. For Android:
  - Select the `androidApp` module and run the app.
4. For iOS:
  - Open the `iosApp` folder in Xcode.
  - Select your target and build the project.

---

## Contributions

Contributions are welcome! Feel free to open issues, submit pull requests, or suggest enhancements to improve RssReader.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Screenshots

<div style="display: flex;">

### Android
<img src="https://github.com/Blondhino/RssReader/blob/feature/ui-improvements/screenshots/android-subscrptions-all.jpeg" width="300">
<img src="https://github.com/Blondhino/RssReader/blob/feature/ui-improvements/screenshots/android-subscriptions-favs.jpeg" width="300" width="300">
<img src="https://github.com/Blondhino/RssReader/blob/feature/ui-improvements/screenshots/android-articles.jpeg" width="300" width="300">

### iOS 
<img src="https://github.com/Blondhino/RssReader/blob/feature/ui-improvements/screenshots/ios-subscriptions-all.jpeg" width="300" width="300">
<img src="https://github.com/Blondhino/RssReader/blob/feature/ui-improvements/screenshots/ios-articles.jpeg" width="300" width="300">
</div>

---

## Acknowledgments

- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- [SQLDelight](https://cashapp.github.io/sqldelight/)
- [Koin](https://insert-koin.io/)
- [Voyager](https://github.com/adrielcafe/voyager)
- [Arrow](https://arrow-kt.io/)
