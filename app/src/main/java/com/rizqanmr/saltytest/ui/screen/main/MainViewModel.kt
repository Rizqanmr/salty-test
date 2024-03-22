package com.rizqanmr.saltytest.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rizqanmr.saltytest.data.UserPagingSource
import com.rizqanmr.saltytest.data.model.UserItem
import com.rizqanmr.saltytest.data.repository.RemoteRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val remoteRepositoryImpl: RemoteRepositoryImpl
) : ViewModel() {

    val mainState = MutableStateFlow<MainState>(MainState.StartState)
    private val pagingConfig = PagingConfig(pageSize = 6)
    private val pager = Pager(config = pagingConfig) {
        UserPagingSource(remoteRepositoryImpl)
    }

    val listUser: Flow<PagingData<UserItem>> = getUsers()

    init {
        getUsers()
    }

    private fun getUsers() : Flow<PagingData<UserItem>> {
        viewModelScope.launch {
            mainState.tryEmit(MainState.LoadingState)
            try {
                pager.flow.cachedIn(viewModelScope).collect {
                    mainState.emit(MainState.Success(it))
                }
            } catch (e: Exception) {
                mainState.emit(MainState.Error(e.localizedMessage!!))
            }
        }
        return pager.flow.cachedIn(viewModelScope)
    }
}