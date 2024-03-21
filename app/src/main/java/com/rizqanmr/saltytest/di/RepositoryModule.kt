package com.rizqanmr.saltytest.di

import com.rizqanmr.saltytest.data.repository.RemoteRepositoryImpl
import com.rizqanmr.saltytest.network.ApiService
import org.koin.dsl.module

val repositoryModule = module {
    single { provideRemoteRepository(get()) }
}

fun provideRemoteRepository(apiService: ApiService): RemoteRepositoryImpl {
    return RemoteRepositoryImpl(apiService)
}