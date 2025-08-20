package com.codingwithyazan.mealapp.domain.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<Params, Result> {

    abstract suspend fun execute(params: Params): DataState<Result>

    fun executeAsFlow(params: Params): Flow<DataState<Result>> = flow {
        try {
            // Emit loading state
            emit(DataState.Loading(ProgressBarState.FullScreenLoading))
            
            // Execute core logic
            val result = execute(params)
            
            // Emit the result
            emit(result)
        } catch (e: Exception) {
            // Handle exceptions
            e.printStackTrace()
            emit(DataState.Error(e.message ?: "Unknown error occurred"))
        } finally {
            // Emit idle loading state
            emit(DataState.Loading(ProgressBarState.Idle))
        }
    }
}
