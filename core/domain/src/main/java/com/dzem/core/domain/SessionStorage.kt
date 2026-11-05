package com.dzem.core.domain

import com.dzem.core.domain.util.AuthInfo

interface SessionStorage {
    suspend fun get(): AuthInfo?
    suspend fun set(info: AuthInfo?)
}