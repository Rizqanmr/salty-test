package com.rizqanmr.saltytest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResult(
    @SerialName("per_page")
    val perPage: Int = 0,
    @SerialName("total")
    val total: Int = 0,
    @SerialName("data")
    val data: List<UserItem>?,
    @SerialName("page")
    val page: Int = 0,
    @SerialName("total_pages")
    val totalPages: Int = 0
)