package com.rizqanmr.saltytest.ui.screen.main

import androidx.lifecycle.ViewModel
import com.rizqanmr.saltytest.data.repository.RemoteRepositoryImpl

class MainViewModel(
    private val remoteRepositoryImpl: RemoteRepositoryImpl
) : ViewModel() {
}