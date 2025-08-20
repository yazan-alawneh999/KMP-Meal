# CMP Meal App

A Kotlin Multiplatform (KMP) application built with Jetpack Compose that displays meals from TheMealDB API. The app features a clean architecture with MVVM pattern and supports both Android and iOS platforms.

## Features

### ✅ Implemented Features

1. **Meals Listing Screen**
   - Tabbed interface for Seafood and Beef categories
   - Fetches meals from TheMealDB API
   - Displays meal names and thumbnails
   - Loading, success, and error state handling
   - Retry functionality for failed requests

2. **Meal Details Screen**
   - Comprehensive meal information display
   - Shows meal name, cuisine area, instructions, and ingredients
   - Scrollable content layout
   - Error handling with retry option

3. **Architecture & Patterns**
   - Clean Architecture with MVVM pattern
   - Repository pattern for data management
   - Use Cases for business logic
   - State management with StateFlow
   - Dependency injection using Koin

4. **UI/UX**
   - Material 3 design system
   - Dark and light theme support
   - Responsive layouts
   - Loading indicators and error states
   - Accessibility support (content descriptions)

5. **Localization**
   - English and Arabic language support
   - Localized strings for all UI elements
   - RTL layout support for Arabic

6. **Networking**
   - Retrofit HTTP client with Gson for JSON parsing
   - Native Kotlin Multiplatform resources for localization
   - Error handling and retry mechanisms

### 🚧 Planned Features

- [ ] Local caching with Room database
- [ ] Pagination for meal listings
- [ ] Search functionality
- [ ] Favorites system
- [ ] Unit tests for Use Cases & ViewModels
- [ ] Image loading with Coil
- [ ] Offline support

## Project Structure

```
composeApp/src/
├── androidMain/          # Android-specific code
├── commonMain/           # Shared code for all platforms
│   ├── kotlin/
│   │   └── com/codingwithyazan/mealapp/
│   │       ├── app/      # App entry point and navigation
│   │       ├── data/     # Data layer (API, Repository)
│   │       ├── domain/   # Business logic (Use Cases, Models)
│   │       ├── di/       # Dependency injection
│   │       └── presentation/ # UI layer (Screens, ViewModels)
│   └── composeResources/ # Localized strings and resources
└── iosMain/              # iOS-specific code
```

## Architecture

The app follows Clean Architecture principles with clear separation of concerns:

- **Data Layer**: API services, repositories, and data models
- **Domain Layer**: Business logic, use cases, and domain models
- **Presentation Layer**: UI components, ViewModels, and state management

### Key Components

- **ViewModel**: Manages UI state and business logic
- **Repository**: Handles data operations and caching
- **Use Cases**: Encapsulate business logic
- **State**: Immutable data classes for UI state
- **Events**: User actions and system events
- **Actions**: Navigation and side effects

## Setup & Installation

### Prerequisites

- Android Studio Hedgehog or later
- Kotlin 1.9.0 or later
- JDK 11 or later
- Xcode 15.0 or later (for iOS development)

### Building the Project

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd CMP-MealApp
   ```

2. Open the project in Android Studio

3. Sync Gradle files and install dependencies

4. Run the app on Android device/emulator or iOS simulator

### Dependencies

The project uses the following key dependencies:

- **Compose Multiplatform**: UI framework
- **Koin**: Dependency injection
- **Retrofit**: HTTP client
- **Gson**: JSON serialization
- **Navigation Compose**: Navigation framework

## API Integration

The app integrates with [TheMealDB](https://www.themealdb.com/) API:

- **Seafood Meals**: `https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood`
- **Beef Meals**: `https://www.themealdb.com/api/json/v1/1/filter.php?c=Beef`
- **Meal Details**: `https://www.themealdb.com/api/json/v1/1/lookup.php?i={mealId}`

## Localization

The app supports multiple languages:

- **English**: Default language
- **Arabic**: RTL support with localized strings

Localization files are located in `composeApp/src/commonMain/composeResources/values/` and `values-ar/`.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes following the established patterns
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- [TheMealDB](https://www.themealdb.com/) for providing the meal data API
- JetBrains for Compose Multiplatform
- The Kotlin and Android communities for excellent tooling and libraries