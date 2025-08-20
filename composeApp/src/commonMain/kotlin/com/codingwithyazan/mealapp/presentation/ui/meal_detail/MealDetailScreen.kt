package com.codingwithyazan.mealapp.presentation.ui.meal_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_mealapp.composeapp.generated.resources.Res
import cmp_mealapp.composeapp.generated.resources.*

import coil3.compose.AsyncImage
import com.codingwithyazan.mealapp.domain.core.UIComponent
import com.codingwithyazan.mealapp.domain.meal.Ingredient
import kotlinx.coroutines.flow.Flow
import com.codingwithyazan.mealapp.domain.meal.MealDetail
import com.codingwithyazan.mealapp.presentation.component.DefaultScreenUI
import com.codingwithyazan.mealapp.presentation.ui.meal_detail.view_model.*
import org.jetbrains.compose.resources.stringResource


@Composable
fun MealDetailScreen(
    mealId: String,
    state: MealDetailState,
    events: (MealDetailEvent) -> Unit,
    errors: Flow<UIComponent>,
) {
    LaunchedEffect(mealId) {
        events(MealDetailEvent.OnInitialize(mealId))
    }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.isLoading
    ) {
        when {
            state.errorMessage != null -> {
                ErrorContent(
                    message = state.errorMessage!!,
                    onRetry = { events(MealDetailEvent.OnRetry) }
                )
            }
            state.meal != null -> {
                MealDetailContent(meal = state.meal!!)
            }
            else -> {
                // Loading or empty state
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(Res.string.no_meal_details))
                }
            }
        }
    }
}

@Composable
private fun MealDetailContent(meal: MealDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Meal Image
        AsyncImage(
            model = meal.strMealThumb,
            contentDescription = stringResource(Res.string.image_of, meal.strMeal ?: ""),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )
        
        // Meal Name
        Text(
            text = meal.strMeal ?: stringResource(Res.string.unknown_meal),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // Area/Cuisine
        meal.strArea?.let { area ->
            Text(
                text = "${stringResource(Res.string.cuisine)}: $area",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        
        // Instructions
        meal.strInstructions?.let { instructions ->
            Text(
                text = stringResource(Res.string.instructions),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = instructions,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        
        // Ingredients
        if (meal.ingredients.isNotEmpty()) {
            Text(
                text = stringResource(Res.string.ingredients),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            meal.ingredients.forEach { ingredient ->
                IngredientItem(ingredient = ingredient)
            }
        }
    }
}

@Composable
private fun IngredientItem(ingredient: Ingredient) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = ingredient.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = ingredient.measure,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Button(onClick = onRetry) {
            Text(stringResource(Res.string.retry))
        }
    }
}

@Composable
private fun AsyncImage(
    model: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    // Placeholder for image loading - you'll need to implement proper image loading
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "🍽️",
            style = MaterialTheme.typography.displayMedium
        )
    }
}
