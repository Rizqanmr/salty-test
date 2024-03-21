package com.rizqanmr.saltytest.di

import org.koin.dsl.module

val appModule = module {
    includes(networkModule)
}