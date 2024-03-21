package com.rizqanmr.saltytest.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizqanmr.saltytest.data.repository.RemoteRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val remoteRepositoryImpl: RemoteRepositoryImpl
) : ViewModel() {

    val mainState = MutableStateFlow<MainState>(MainState.StartState)

    suspend fun getUsers() {
        viewModelScope.launch {
            mainState.tryEmit(MainState.LoadingState)
            remoteRepositoryImpl.getUsers().onSuccess { userResult ->
                mainState.emit(MainState.Success(userResult.data))
            }.onFailure {
                mainState.emit(MainState.Error(it.localizedMessage.orEmpty()))
            }
        }
    }
}