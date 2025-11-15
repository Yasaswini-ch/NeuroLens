package com.example.neurolens.ml

import android.content.Context
import kotlinx.coroutines.flow.Flow

/**
 * Base interface for all ML processors in NeuroLens.
 * All processing happens on-device for privacy.
 */
interface MLProcessor<Input, Output> {
    /**
     * Initialize the ML model
     */
    suspend fun initialize(context: Context)

    /**
     * Process input and return output
     */
    suspend fun process(input: Input): Result<Output>

    /**
     * Process input as a flow for real-time processing
     */
    fun processStream(input: Flow<Input>): Flow<Result<Output>>

    /**
     * Release resources
     */
    fun release()

    /**
     * Check if processor is ready
     */
    val isReady: Boolean
}

/**
 * Base implementation with common functionality
 */
abstract class BaseMLProcessor<Input, Output> : MLProcessor<Input, Output> {
    protected var initialized = false

    override val isReady: Boolean
        get() = initialized

    protected suspend fun <T> runSafely(block: suspend () -> T): Result<T> {
        return try {
            if (!initialized) {
                Result.failure(IllegalStateException("Processor not initialized"))
            } else {
                Result.success(block())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
