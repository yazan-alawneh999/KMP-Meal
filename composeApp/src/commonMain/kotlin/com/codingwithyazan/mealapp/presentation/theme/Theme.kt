package com.codingwithyazan.mealapp.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFF4747),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFCA3D49),
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF03DAC6),
    onSecondaryContainer = Color.Black,
    tertiary = Color(0xFFC62F79),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFC62F79),
    onTertiaryContainer = Color.White,
    background = Color(0xFFf6f6f6),
    onBackground = Color(0xFF404649),
    surface = Color(0xFFf5f5f5),
    onSurface = Color(0xFF404649),
    surfaceVariant = Color(0xFFf6f6f6),
    onSurfaceVariant = Color(0xFF404649),
    outline = Color(0xFFDBDBDC),
    outlineVariant = Color(0xFFC4CDD3)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF4747),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFFCA3D49),
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF03DAC6),
    onSecondaryContainer = Color.Black,
    tertiary = Color(0xFFC62F79),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFC62F79),
    onTertiaryContainer = Color.White,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF2D2D2D),
    onSurfaceVariant = Color.White,
    outline = Color(0xFF404040),
    outlineVariant = Color(0xFF555555)
)

@Composable
expect fun isSystemInDarkTheme(): Boolean

@Composable
fun MealAppTheme(
    content: @Composable () -> Unit
) {
    val darkTheme = isSystemInDarkTheme()
    
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}