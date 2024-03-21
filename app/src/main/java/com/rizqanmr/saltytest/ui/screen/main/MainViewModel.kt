package com.rizqanmr.saltytest.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizqanmr.saltytest.data.model.UserItem
import com.rizqanmr.saltytest.data.repository.RemoteRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val remoteRepositoryImpl: RemoteRepositoryImpl
) : ViewModel() {

    private val _users: MutableStateFlow<List<UserItem>> = MutableStateFlow(emptyList())
    val users: StateFlow<List<UserItem>> = _users

    suspend fun getUsers() {
        viewModelScope.launch {
            remoteRepositoryImpl.getUsers().onSuccess { userResult ->
                _users.update { userResult.data }
            }.onFailure {
                //Handle error
            }
        }
    }
}