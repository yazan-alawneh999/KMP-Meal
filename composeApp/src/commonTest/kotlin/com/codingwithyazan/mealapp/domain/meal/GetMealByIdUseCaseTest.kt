package com.codingwithyazan.mealapp.domain.meal

import com.codingwithyazan.mealapp.domain.core.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GetMealByIdUseCaseTest {

    private val testMealId = "123"
    private val testMealDetail = MealDetail(
        idMeal = testMealId,
        strMeal = "Test Meal",
        strInstructions = "Test Instructions",
        strMealThumb = "http://test.com/meal.jpg"
    )

    @Test
    fun `execute returns success when meal is found`() = runTest {
        // Given
        val mockRepository = object : MealRepository {
            override suspend fun getMealsByCategory(category: String): List<Meal> = emptyList()
            override suspend fun getMealById(mealId: String): MealDetail? {
                assertEquals(testMealId, mealId)
                return testMealDetail
            }
        }

        val useCase = GetMealByIdUseCase(mockRepository)

        // When
        val result = useCase.execute(GetMealByIdUseCase.Params(testMealId))

        // Then
        assertTrue(result is DataState.Success)
        assertEquals(testMealDetail, result.data)
    }

    @Test
    fun `execute returns error when meal is not found`() = runTest {
        // Given
        val mockRepository = object : MealRepository {
            override suspend fun getMealsByCategory(category: String): List<Meal> = emptyList()
            override suspend fun getMealById(mealId: String): MealDetail? = null
        }

        val useCase = GetMealByIdUseCase(mockRepository)

        // When
        val result = useCase.execute(GetMealByIdUseCase.Params("nonexistent_id"))

        // Then
        assertTrue(result is DataState.Error)
        assertEquals("Meal not found with ID: nonexistent_id", (result as DataState.Error).message)
    }

    @Test
    fun `execute returns error when repository throws exception`() = runTest {
        // Given
        val errorMessage = "Network error"
        val mockRepository = object : MealRepository {
            override suspend fun getMealsByCategory(category: String): List<Meal> = emptyList()
            override suspend fun getMealById(mealId: String): MealDetail? {
                throw Exception(errorMessage)
            }
        }

        val useCase = GetMealByIdUseCase(mockRepository)

        // When
        val result = useCase.execute(GetMealByIdUseCase.Params(testMealId))

        // Then
        assertTrue(result is DataState.Error)
        assertEquals(errorMessage, (result as DataState.Error).message)
    }

    @Test
    fun `execute passes correct meal ID to repository`() = runTest {
        // Given
        val expectedMealId = "specific_id"
        var actualMealId = ""
        
        val mockRepository = object : MealRepository {
            override suspend fun getMealsByCategory(category: String): List<Meal> = emptyList()
            override suspend fun getMealById(mealId: String): MealDetail? {
                actualMealId = mealId
                return null
            }
        }

        val useCase = GetMealByIdUseCase(mockRepository)

        // When
        useCase.execute(GetMealByIdUseCase.Params(expectedMealId))

        // Then
        assertEquals(expectedMealId, actualMealId)
    }
}
