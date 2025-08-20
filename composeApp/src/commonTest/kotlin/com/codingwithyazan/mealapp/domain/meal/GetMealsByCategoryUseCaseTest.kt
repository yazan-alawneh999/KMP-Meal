package com.codingwithyazan.mealapp.domain.meal

import com.codingwithyazan.mealapp.domain.core.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GetMealsByCategoryUseCaseTest {

    private val mockMeal = Meal(
        idMeal = "1",
        strMeal = "Test Meal",
        strMealThumb = "http://test.com/meal.jpg"
    )

    private val testCategory = "TestCategory"

    @Test
    fun `execute returns success with meals when repository returns meals`() = runTest {
        // Given
        val mockRepository = object : MealRepository {
            override suspend fun getMealsByCategory(category: String): List<Meal> {
                assertEquals(testCategory, category)
                return listOf(mockMeal)
            }
            override suspend fun getMealById(mealId: String): MealDetail? = null
        }

        val useCase = GetMealsByCategoryUseCase(mockRepository)

        // When
        val result = useCase.execute(GetMealsByCategoryUseCase.Params(testCategory))

        // Then
        assertTrue(result is DataState.Success)
        assertEquals(1, result.data?.size)
        assertEquals(mockMeal, result.data?.first())
    }

    @Test
    fun `execute returns success with empty list when repository returns empty list`() = runTest {
        // Given
        val mockRepository = object : MealRepository {
            override suspend fun getMealsByCategory(category: String): List<Meal> = emptyList()
            override suspend fun getMealById(mealId: String): MealDetail? = null
        }

        val useCase = GetMealsByCategoryUseCase(mockRepository)

        // When
        val result = useCase.execute(GetMealsByCategoryUseCase.Params(testCategory))

        // Then
        assertTrue(result is DataState.Success)
        assertTrue(result.data?.isEmpty() == true)
    }

    @Test
    fun `execute passes correct category to repository`() = runTest {
        // Given
        val expectedCategory = "SpecificCategory"
        var actualCategory = ""
        
        val mockRepository = object : MealRepository {
            override suspend fun getMealsByCategory(category: String): List<Meal> {
                actualCategory = category
                return emptyList()
            }
            override suspend fun getMealById(mealId: String): MealDetail? = null
        }

        val useCase = GetMealsByCategoryUseCase(mockRepository)

        // When
        useCase.execute(GetMealsByCategoryUseCase.Params(expectedCategory))

        // Then
        assertEquals(expectedCategory, actualCategory)
    }
}
