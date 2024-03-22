package com.rizqanmr.saltytest.ui.screen.login

sealed class LoginState {
    object LoadingState: LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val errorMessage: String) : LoginState()
}