package com.dzem.core.data.auth

import com.dzem.core.domain.util.AuthInfo

fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable =
    AuthInfoSerializable(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )

fun AuthInfoSerializable.toAuthInfo(): AuthInfo =
    AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
