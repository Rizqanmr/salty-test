package com.rizqanmr.saltytest.data.repository

import com.rizqanmr.saltytest.data.model.Credentials
import com.rizqanmr.saltytest.data.model.LoginResponse
import com.rizqanmr.saltytest.data.model.UserResult
import com.rizqanmr.saltytest.network.ApiService
import io.ktor.client.call.body

class RemoteRepositoryImpl(
    private val apiService: ApiService
) : RemoteRepository {
    override suspend fun getUsers(page: Int): Result<UserResult> {
        return try {
            Result.success(apiService.getUsers(page).body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(credentials: Credentials): Result<LoginResponse> {
        return try {
            Result.success(apiService.login(credentials).body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}