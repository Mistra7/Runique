package com.dzem.auth.data.di

import com.dzem.auth.data.EmailPatternValidator
import com.dzem.auth.domain.PatternValidator
import com.dzem.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
}