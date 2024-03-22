package com.rizqanmr.saltytest.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizqanmr.saltytest.data.model.Credentials
import com.rizqanmr.saltytest.data.repository.RemoteRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val remoteRepositoryImpl: RemoteRepositoryImpl
) : ViewModel() {

    val loginState = MutableStateFlow<LoginState>(LoginState.LoadingState)

    suspend fun login(credentials: Credentials) {
        viewModelScope.launch {
            loginState.tryEmit(LoginState.LoadingState)
            remoteRepositoryImpl.login(credentials).onSuccess { loginResult ->
                loginState.emit(LoginState.Success(loginResult.token))
            }.onFailure {
                loginState.emit(LoginState.Error(it.localizedMessage.orEmpty()))
            }
        }
    }
}