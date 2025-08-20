package com.codingwithyazan.mealapp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codingwithyazan.mealapp.presentation.theme.BorderColor
import com.codingwithyazan.mealapp.presentation.theme.DefaultImageButtonTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource



@OptIn(ExperimentalResourceApi::class)
@Composable
fun SimpleImageButton(image: DrawableResource, onClick: () -> Unit = {}) {
    Button(
        modifier = Modifier.size(70.dp),
        onClick = onClick,
        colors = DefaultImageButtonTheme(),
        border = BorderStroke(1.dp, BorderColor),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        shape = CircleShape
    ) {
        Image(
            painterResource(image),
            contentDescription = null,
            modifier = Modifier.size(70.dp)
        )
    }
}