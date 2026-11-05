package com.dzem.core.data.di

import com.dzem.core.data.auth.EncryptedSessionStorage
import com.dzem.core.data.networking.HttpClientFactory
import com.dzem.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}