package com.codingwithyazan.mealapp.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
actual fun isSystemInDarkTheme(): Boolean {
    // For desktop, we'll use a default light theme
    // In a real implementation, you could use platform-specific APIs
    // to detect system theme on desktop platforms
    return false
}
