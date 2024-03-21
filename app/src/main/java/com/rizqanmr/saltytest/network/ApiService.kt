package com.rizqanmr.saltytest.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {

    companion object {
        private const val BASE_URL = "https://reqres.in/api/"
        private const val USER = "users"
        private const val PAGE = 1
    }

    suspend fun getUsers() = client.get("$BASE_URL$USER?page=$PAGE")
}