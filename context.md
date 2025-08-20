# Recipe for Creating a New Screen

This document provides a step-by-step guide for creating new screens using the established pattern in this project.

## Overview

To create a new screen, you need to create **four corresponding files**:
1. **State** - Defines all the data your UI needs
2. **Event** - Defines all the actions the user can take
3. **ViewModel** - Connects State and Event files
4. **Screen UI** - The Composable function for your screen

## 1. The State File (`YourScreenState.kt`)

This defines all the data your UI needs.

**Location:** `presentation/ui/main/your_screen/view_model/YourScreenState.kt`

```kotlin
import com.codingwithyazan.mealapp.domain.core.ProgressBarState
import com.codingwithyazan.mealapp.domain.core.ViewState

data class YourScreenState(
    val isLoading: ProgressBarState = ProgressBarState.Idle,
    val screenTitle: String = "My Screen",
    // Add any other data the UI needs
) : ViewState
```

## 2. The Event File (`YourScreenEvent.kt`)

This defines all the actions the user can take.

**Location:** `presentation/ui/main/your_screen/view_model/YourScreenEvent.kt`

```kotlin
import com.codingwithyazan.mealapp.domain.core.ViewEvent

sealed class YourScreenEvent : ViewEvent {
    data object OnInitialize : YourScreenEvent()
    data object OnButtonClicked : YourScreenEvent()
    data class OnUpdateText(val newText: String) : YourScreenEvent()
}
```

## 3. The ViewModel File (`YourScreenViewModel.kt`)

This connects the State and Event files. It inherits from `BaseViewModel` and implements the required abstract functions.

**Location:** `presentation/ui/main/your_screen/view_model/YourScreenViewModel.kt`

```kotlin
import com.codingwithyazan.mealapp.domain.core.BaseViewModel

class YourScreenViewModel : BaseViewModel<YourScreenEvent, YourScreenState, YourScreenAction>() {

    override fun setInitialState() = YourScreenState()

    override fun onTriggerEvent(event: YourScreenEvent) {
        when (event) {
            is YourScreenEvent.OnInitialize -> {
                // Handle initialization logic
            }
            is YourScreenEvent.OnButtonClicked -> {
                // Handle button click logic
            }
            is YourScreenEvent.OnUpdateText -> {
                setState { copy(screenTitle = event.newText) }
            }
        }
    }
}
```

**Note:** You would also create a `YourScreenAction.kt` for navigation or other side effects.

## 4. The Screen UI File (`YourScreen.kt`)

This is the Composable function for your screen. It calls and uses the existing `DefaultScreenUI` and connects everything together.

**Location:** `presentation/ui/main/your_screen/YourScreen.kt`

```kotlin
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import com.codingwithyazan.mealapp.presentation.component.DefaultScreenUI

@Composable
fun YourScreen(
    state: YourScreenState,
    events: (YourScreenEvent) -> Unit,
    // ... other parameters like error flows
) {
    // Here, you USE the existing DefaultScreenUI component
    DefaultScreenUI(
        progressBarState = state.isLoading,
        // Pass other necessary parameters like errors, network state, etc.
    ) {
        // --- Your screen's unique content goes inside this block ---

        Text(text = state.screenTitle)

        Button(onClick = { events(YourScreenEvent.OnButtonClicked) }) {
            Text("Click Me")
        }

        // --- End of your unique content ---
    }
}
```

## Summary

This four-part structure is the **standard way** to create new features in this application, ensuring that:

- ✅ State management is handled consistently
- ✅ UI boilerplate is managed by base classes
- ✅ `DefaultScreenUI` provides common functionality
- ✅ Code follows established patterns and conventions

---

# Project Requirements

## General Requirements

- **Platform:** Android Kotlin project with Jetpack Compose for UI
- **Networking:** Use Retrofit in the shared module
- **Dependency Injection:** Use Koin
- **Architecture:** MVVM/MVI
- **State Management:** Kotlin StateFlow or Compose's mutableStateOf
- **Theming:** Support both dark and light mode using shared theming
- **Localization:** Add basic localization (English, Arabic) for UI text – Static Labels

## Screens & Features

### A. Meals Listing

**Data Source:**
- Seafood: `https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood`
- Beef: `https://www.themealdb.com/api/json/v1/1/filter.php?c=Beef`

**UI Requirements:**
- Single screen (shared Composable)
- Two sections (tabs or vertical groups): "Seafood" and "Beef"
- Each meal shows:
  - Meal name (localized)
  - Thumbnail image (use KMP-compatible image loader)
- Handle loading, success, and error states in Compose

### B. Meal Details

**Data Source:**
- Fetch meal details by ID: `https://www.themealdb.com/api/json/v1/1/lookup.php?i={mealId}`

**UI Requirements:**
- Shared Composable for details screen
- Show:
  - Meal name (localized)
  - Area (cuisine)
  - Instructions (scrollable)
  - Full-size image
  - (Optional) Ingredients list

## Technical Requirements

### Project Structure
- Single Android project using Kotlin and Jetpack Compose for UI
- Follow clean architecture principles with separation of concerns:
  - **Data Layer**
  - **Domain Layer**
  - **Presentation Layer**

### Networking
- Use Retrofit with OkHttp for API calls
- Use kotlinx.serialization or Gson/Moshi for JSON parsing

### Dependency Injection
- Use Koin for dependency management

### Architecture
- **MVVM (or MVI)** architecture
- **Repositories** handle data layer (API, optional caching)
- **ViewModels** manage business logic and state exposure
- **UI** is built with Compose and observes state from ViewModels

### State & Error Handling
- Use StateFlow or mutableStateOf for reactive state management
- Show proper loading indicators while fetching data
- Handle and display errors gracefully with retry options

### UI & Theming
- Implement dark and light mode using Material 3 theming
- Use string resources for localization (English and Arabic at minimum)
- Ensure accessibility support (content descriptions, text scaling)

## Bonus Features

- ✅ Add local caching using RoomDB
- ✅ Add pagination to listing
- ✅ Write unit tests for Use Cases & ViewModels
- ✅ Improve accessibility (content descriptions, dynamic text size) 