package com.codingwithyazan.mealapp.domain.meal

import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    val idMeal: String? = null,
    val strMeal: String? = null,
    val strMealThumb: String? = null
) {
    // Helper function to check if meal is valid
    fun isValid(): Boolean {
        return !idMeal.isNullOrBlank() && 
               !strMeal.isNullOrBlank() && 
               !strMealThumb.isNullOrBlank()
    }
    
    // Safe getters that return empty strings instead of null
    fun safeIdMeal(): String = idMeal ?: ""
    fun safeStrMeal(): String = strMeal ?: ""
    fun safeStrMealThumb(): String = strMealThumb ?: ""
}

@Serializable
data class MealDetail(
    val idMeal: String,
    val strMeal: String,
    val strCategory: String? = null,
    val strArea: String? = null,
    val strInstructions: String? = null,
    val strMealThumb: String? = null,
    val strTags: String? = null,
    val strYoutube: String? = null,
    val strIngredient1: String? = null,
    val strIngredient2: String? = null,
    val strIngredient3: String? = null,
    val strIngredient4: String? = null,
    val strIngredient5: String? = null,
    val strIngredient6: String? = null,
    val strIngredient7: String? = null,
    val strIngredient8: String? = null,
    val strIngredient9: String? = null,
    val strIngredient10: String? = null,
    val strIngredient11: String? = null,
    val strIngredient12: String? = null,
    val strIngredient13: String? = null,
    val strIngredient14: String? = null,
    val strIngredient15: String? = null,
    val strIngredient16: String? = null,
    val strIngredient17: String? = null,
    val strIngredient18: String? = null,
    val strIngredient19: String? = null,
    val strIngredient20: String? = null,
    val strMeasure1: String? = null,
    val strMeasure2: String? = null,
    val strMeasure3: String? = null,
    val strMeasure4: String? = null,
    val strMeasure5: String? = null,
    val strMeasure6: String? = null,
    val strMeasure7: String? = null,
    val strMeasure8: String? = null,
    val strMeasure9: String? = null,
    val strMeasure10: String? = null,
    val strMeasure11: String? = null,
    val strMeasure12: String? = null,
    val strMeasure13: String? = null,
    val strMeasure14: String? = null,
    val strMeasure15: String? = null,
    val strMeasure16: String? = null,
    val strMeasure17: String? = null,
    val strMeasure18: String? = null,
    val strMeasure19: String? = null,
    val strMeasure20: String? = null
) {
    val ingredients: List<Ingredient>
        get() {
            val ingredients = mutableListOf<Ingredient>()
            for (i in 1..20) {
                val ingredient = getIngredient(i)
                val measure = getMeasure(i)
                if (!ingredient.isNullOrBlank()) {
                    ingredients.add(Ingredient(ingredient, measure ?: ""))
                }
            }
            return ingredients
        }

    private fun getIngredient(index: Int): String? {
        return when (index) {
            1 -> strIngredient1
            2 -> strIngredient2
            3 -> strIngredient3
            4 -> strIngredient4
            5 -> strIngredient5
            6 -> strIngredient6
            7 -> strIngredient7
            8 -> strIngredient8
            9 -> strIngredient9
            10 -> strIngredient10
            11 -> strIngredient11
            12 -> strIngredient12
            13 -> strIngredient13
            14 -> strIngredient14
            15 -> strIngredient15
            16 -> strIngredient16
            17 -> strIngredient17
            18 -> strIngredient18
            19 -> strIngredient19
            20 -> strIngredient20
            else -> null
        }
    }

    private fun getMeasure(index: Int): String? {
        return when (index) {
            1 -> strMeasure1
            2 -> strMeasure2
            3 -> strMeasure3
            4 -> strMeasure4
            5 -> strMeasure5
            6 -> strMeasure6
            7 -> strMeasure7
            8 -> strMeasure8
            9 -> strMeasure9
            10 -> strMeasure10
            11 -> strMeasure11
            12 -> strMeasure12
            13 -> strMeasure13
            14 -> strMeasure14
            15 -> strMeasure15
            16 -> strMeasure16
            17 -> strMeasure17
            18 -> strMeasure18
            19 -> strMeasure19
            20 -> strMeasure20
            else -> null
        }
    }
}

data class Ingredient(
    val name: String,
    val measure: String
)

@Serializable
data class MealsResponse(
    val meals: List<Meal>?
)

@Serializable
data class MealDetailResponse(
    val meals: List<MealDetail>?
)

