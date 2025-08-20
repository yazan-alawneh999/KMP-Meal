# CMP Meal App

A Kotlin Multiplatform (KMP) application built with Jetpack Compose that displays meals from TheMealDB API. The app features a clean architecture with MVVM pattern and supports both Android and iOS platforms.

## Features

### ✅ Implemented Features

1. **Meals Listing Screen**
   - Tabbed interface for Seafood and Beef categories
   - Fetches meals from TheMealDB API with parallel loading
   - Displays meal names and thumbnails (optimized image sizing: 120x80dp)
   - Loading, success, and error state handling
   - Retry and refresh functionality
   - Robust null safety and data validation

2. **Meal Details Screen**
   - Comprehensive meal information display
   - Shows meal name, cuisine area, instructions, and ingredients
   - Scrollable content layout
   - Error handling with retry option

3. **Architecture & Patterns**
   - Clean Architecture with MVVM pattern
   - Repository pattern with enhanced error handling
   - Separated Use Cases for business logic (seafood/beef loading)
   - State management with MutableState and Flow
   - Dependency injection using Koin
   - Null-safe data models with validation

4. **UI/UX**
   - Material 3 design system
   - Automatic dark and light theme support based on system settings
   - Responsive layouts with optimized meal card design
   - Loading indicators and error states
   - Accessibility support (content descriptions)
   - Clean, debug-free production code

5. **Localization**
   - English and Arabic language support
   - Localized strings for all UI elements
   - RTL layout support for Arabic

6. **Networking & Data Handling**
   - Retrofit HTTP client with Gson for JSON parsing
   - Robust null safety for API responses
   - Data validation and filtering for invalid meals
   - Comprehensive error logging and debugging
   - Graceful handling of malformed API data

7. **Code Quality & Reliability**
   - Null-safe data models with helper methods
   - Comprehensive data validation
   - Separated concerns with dedicated loading functions
   - Clean error handling without silent failures
   - Production-ready logging and monitoring

### 🚧 Planned Features

- [ ] Local caching with Room database
- [ ] Pagination for meal listings
- [ ] Search functionality
- [ ] Favorites system
- [ ] Unit tests for Use Cases & ViewModels (test framework added)
- [x] Image loading with Coil (implemented)
- [ ] Offline support

### ✅ Recently Completed

- [x] **Null Safety & Data Validation**: Robust handling of malformed API data
- [x] **Enhanced Error Handling**: No more silent failures, comprehensive logging
- [x] **Optimized UI Layout**: Improved meal image sizing (120x80dp)
- [x] **Separated Loading Logic**: Independent beef and seafood data loading
- [x] **Production-Ready Code**: Removed all debug UI elements and logging
- [x] **Platform-Specific Theming**: Automatic theme detection for all platforms

## Project Structure

```
composeApp/src/
├── androidMain/          # Android-specific code
│   └── kotlin/com/codingwithyazan/mealapp/
│       ├── data/remote/  # Android HTTP client (Retrofit)
│       ├── di/           # Android dependency injection
│       ├── presentation/
│       │   ├── component/ # Android-specific UI components
│       │   └── theme/    # Android theme detection
│       └── MainActivity.kt
├── commonMain/           # Shared code for all platforms
│   ├── kotlin/
│   │   └── com/codingwithyazan/mealapp/
│   │       ├── app/      # App entry point and navigation
│   │       │   ├── App.kt
│   │       │   └── Route.kt
│   │       ├── data/     # Data layer
│   │       │   ├── remote/    # API service definitions
│   │       │   └── repository/ # Repository implementations
│   │       ├── domain/   # Business logic layer
│   │       │   ├── core/      # Base classes (ViewModel, UseCase, DataState)
│   │       │   ├── meal/      # Meal-specific domain logic
│   │       │   │   ├── Meal.kt           # Null-safe data models
│   │       │   │   ├── MealRepository.kt # Repository interface
│   │       │   │   └── GetMealsByCategoryUseCase.kt
│   │       │   └── datasource/ # Data source interfaces
│   │       ├── di/       # Dependency injection (Koin modules)
│   │       └── presentation/ # UI layer
│   │           ├── component/     # Reusable UI components
│   │           ├── theme/         # Theme system (Material 3)
│   │           └── ui/
│   │               ├── meals_listing/    # Meals listing screen
│   │               │   ├── MealsListingScreen.kt
│   │               │   └── view_model/
│   │               │       ├── MealsListingViewModel.kt
│   │               │       ├── MealsListingState.kt
│   │               │       ├── MealsListingEvent.kt
│   │               │       └── MealsListingAction.kt
│   │               └── meal_detail/      # Meal detail screen
│   │                   ├── MealDetailScreen.kt
│   │                   └── view_model/
│   └── composeResources/ # Localized strings and resources
│       ├── values/       # English strings
│       └── values-ar/    # Arabic strings
├── desktopMain/          # Desktop-specific code
│   └── kotlin/com/codingwithyazan/mealapp/
│       └── presentation/
│           ├── component/ # Desktop-specific UI components
│           └── theme/    # Desktop theme implementation
└── iosMain/              # iOS-specific code
    └── kotlin/com/codingwithyazan/mealapp/
        ├── data/remote/  # iOS HTTP client (Ktor)
        ├── di/           # iOS dependency injection
        ├── presentation/
        │   ├── component/ # iOS-specific UI components
        │   └── theme/    # iOS theme implementation
        └── MainViewController.kt
```

## Theme System

The app automatically supports both dark and light modes based on the user's system settings:

- **Automatic Detection**: The app automatically detects the system theme (dark/light) and applies the appropriate color scheme
- **Material 3**: Uses Material 3 design system with comprehensive color schemes for both themes
- **Platform Support**: 
  - Android: Automatically detects system theme changes and updates in real-time
  - Desktop/iOS: Defaults to light theme (can be extended with platform-specific theme detection)
- **No UI Toggle**: Users don't need to manually switch themes - it follows their system preference

### Theme Implementation

The theme system is implemented using Kotlin Multiplatform's `expect/actual` pattern:
- `Theme.kt` (common): Defines the theme structure and color schemes
- `Theme.android.kt`: Android-specific theme detection using system configuration
- `Theme.desktop.kt`: Desktop theme implementation
- `Theme.ios.kt`: iOS theme implementation

## Architecture

The app follows Clean Architecture principles with clear separation of concerns:

- **Data Layer**: API services, repositories, and data models
- **Domain Layer**: Business logic, use cases, and domain models
- **Presentation Layer**: UI components, ViewModels, and state management

### Key Components

- **ViewModel**: Manages UI state and business logic with separated loading functions
- **Repository**: Handles data operations with robust null safety and validation
- **Use Cases**: Encapsulate business logic for specific domains (seafood/beef)
- **State**: Immutable data classes for UI state
- **Events**: User actions and system events
- **Actions**: Navigation and side effects

### Data Flow & Error Handling

The app implements a robust data flow with comprehensive error handling:

1. **API Layer**: Raw data from TheMealDB API
2. **Repository Layer**: Data validation, null safety checks, and filtering
3. **Use Case Layer**: Business logic and data transformation
4. **ViewModel Layer**: State management and UI logic
5. **UI Layer**: Safe data display with helper methods

### Key Improvements

- **Null Safety**: All data models use nullable properties with validation
- **Data Validation**: Invalid meals are filtered out before reaching the UI
- **Error Visibility**: Comprehensive logging at every layer for debugging
- **Separated Concerns**: Beef and seafood loading are handled independently
- **Safe UI Access**: Helper methods (`safeIdMeal()`, `safeStrMeal()`, etc.) prevent crashes

### Data Model Design

```kotlin
@Serializable
data class Meal(
    val idMeal: String? = null,
    val strMeal: String? = null,
    val strMealThumb: String? = null
) {
    fun isValid(): Boolean = !idMeal.isNullOrBlank() && 
                            !strMeal.isNullOrBlank() && 
                            !strMealThumb.isNullOrBlank()
    
    fun safeIdMeal(): String = idMeal ?: ""
    fun safeStrMeal(): String = strMeal ?: ""
    fun safeStrMealThumb(): String = strMealThumb ?: ""
}
```

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

## Troubleshooting

### Common Issues

1. **Empty Meal Lists**
   - Check Logcat for repository logs: `Repository: API response for [category]`
   - Look for data validation messages: `Repository: Filtering out invalid meal`
   - Verify API response structure matches data models

2. **Null Pointer Exceptions**
   - Ensure you're using safe getter methods: `meal.safeStrMeal()` instead of `meal.strMeal`
   - Check that data validation is working in the repository layer

3. **Theme Not Switching**
   - Verify platform-specific theme implementations are in place
   - Check Android configuration changes in `Theme.android.kt`

4. **Build Failures**
   - Ensure all imports are correctly resolved
   - Check that platform-specific implementations match `expect` declarations
   - Verify Kotlin/Compose versions are compatible

### Debugging

The app includes comprehensive logging at every layer:
- **Repository**: `Repository: API response for [category] - raw meals: X, valid meals: Y`
- **Use Case**: `UseCase: Executing for category: [category]`
- **ViewModel**: `ViewModel: [API] successful, got X meals`

Monitor these logs to trace data flow and identify issues.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes following the established patterns
4. Ensure null safety and data validation are maintained
5. Add comprehensive logging for debugging
6. Add tests if applicable
7. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- [TheMealDB](https://www.themealdb.com/) for providing the meal data API
- JetBrains for Compose Multiplatform
- The Kotlin and Android communities for excellent tooling and libraries