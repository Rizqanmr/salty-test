package com.rizqanmr.saltytest.ui.screen.main

import androidx.paging.PagingData
import com.rizqanmr.saltytest.data.model.UserItem

sealed class MainState {
    object StartState : MainState()
    object LoadingState : MainState()
    data class Success(val users: PagingData<UserItem>) : MainState()
    data class Error(val errorMessage: String) : MainState()
}