package com.rizqanmr.saltytest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItem(
    @SerialName("last_name")
    val lastName: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("avatar")
    val avatar: String = "",
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("email")
    val email: String = ""
)