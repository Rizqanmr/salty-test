package com.rizqanmr.saltytest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    @SerialName("email")
    var email: String = "",
    @SerialName("password")
    var password: String = "",
    @SerialName("remember")
    var remember: Boolean = false
)
