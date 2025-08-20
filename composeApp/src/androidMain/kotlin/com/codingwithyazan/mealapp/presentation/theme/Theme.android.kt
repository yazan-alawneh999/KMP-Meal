package com.codingwithyazan.mealapp.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun isSystemInDarkTheme(): Boolean {
    val context = LocalContext.current
    var isDarkTheme by remember { mutableStateOf(false) }
    
    DisposableEffect(context) {
        val currentNightMode = context.resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
        isDarkTheme = currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES
        
        // Listen for configuration changes to update theme dynamically
        val callback = object : android.content.ComponentCallbacks {
            override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
                val newNightMode = newConfig.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
                isDarkTheme = newNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES
            }
            
            override fun onLowMemory() {}
        }
        
        context.registerComponentCallbacks(callback)
        
        onDispose {
            context.unregisterComponentCallbacks(callback)
        }
    }
    
    return isDarkTheme
}
