package com.rizqanmr.saltytest.network

import com.rizqanmr.saltytest.data.model.Credentials
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class ApiService(private val client: HttpClient) {

    companion object {
        private const val BASE_URL = "https://reqres.in/api/"
        private const val USER = "users"
        private const val LOGIN = "login"
    }

    suspend fun getUsers(page: Int) = client.get("$BASE_URL$USER?page=$page")

    suspend fun login(credentials: Credentials) = client.post("$BASE_URL$LOGIN") {
        setBody(credentials)
    }
}