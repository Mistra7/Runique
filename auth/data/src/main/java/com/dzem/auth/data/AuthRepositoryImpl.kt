package com.dzem.auth.data

import com.dzem.auth.domain.AuthRepository
import com.dzem.core.data.networking.post
import com.dzem.core.domain.util.DataError
import com.dzem.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {
    override suspend fun register(
        email: String,
        password: String
    ): EmptyResult<DataError.Network> =
        httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password
            )
        )
}