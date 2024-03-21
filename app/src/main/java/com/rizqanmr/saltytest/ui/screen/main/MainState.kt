package com.rizqanmr.saltytest.ui.screen.main

import com.rizqanmr.saltytest.data.model.UserItem

sealed class MainState {
    object StartState : MainState()
    object LoadingState : MainState()
    data class Success(val users: List<UserItem>) : MainState()
    data class Error(val errorMessage: String) : MainState()
}