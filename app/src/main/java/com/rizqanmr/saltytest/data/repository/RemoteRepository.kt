package com.rizqanmr.saltytest.data.repository

import com.rizqanmr.saltytest.data.model.UserResult

interface RemoteRepository {

    suspend fun getUsers(): Result<UserResult>
}