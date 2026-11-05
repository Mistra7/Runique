package com.dzem.auth.domain

import com.dzem.core.domain.util.DataError
import com.dzem.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}