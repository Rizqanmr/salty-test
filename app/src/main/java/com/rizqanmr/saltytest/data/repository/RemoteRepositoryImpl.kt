package com.rizqanmr.saltytest.data.repository

import com.rizqanmr.saltytest.data.model.UserResult
import com.rizqanmr.saltytest.network.ApiService
import io.ktor.client.call.body

class RemoteRepositoryImpl(
    private val apiService: ApiService
) : RemoteRepository {
    override suspend fun getUsers(): Result<UserResult> {
        return try {
            Result.success(apiService.getUsers().body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}