package com.rizqanmr.saltytest.data.repository

import com.rizqanmr.saltytest.data.model.Credentials
import com.rizqanmr.saltytest.data.model.LoginResponse
import com.rizqanmr.saltytest.data.model.UserResult

interface RemoteRepository {

    suspend fun getUsers(): Result<UserResult>

    suspend fun login(credentials: Credentials): Result<LoginResponse>
}