package com.codingwithyazan.mealapp.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

/**
 * Android implementation of MealImage using Coil
 */
@Composable
actual fun MealImage(
    url: String?,
    contentDescription: String?,
    modifier: Modifier,
    contentScale: ContentScale
) {
    if (url.isNullOrBlank()) {
        // Show placeholder when URL is null or empty
        Box(
            modifier = modifier.clip(RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🍽️",
                style = MaterialTheme.typography.displayMedium
            )
        }
    } else {
        AsyncImage(
            model = url,
            contentDescription = contentDescription,
            modifier = modifier.clip(RoundedCornerShape(8.dp)),
            contentScale = contentScale,
            onError = { state ->
                println("DEBUG AsyncImage: Error loading image from $url: ${state.result}")
            },
            onSuccess = { state ->
                println("DEBUG AsyncImage: Successfully loaded image from $url")
            }
        )
    }
}
