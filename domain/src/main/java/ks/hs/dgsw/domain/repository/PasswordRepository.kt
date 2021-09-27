package ks.hs.dgsw.domain.repository

import ks.hs.dgsw.domain.entity.request.Password

interface PasswordRepository {
    suspend fun postPasswordRegister(password: Password): String
    suspend fun postPasswordLogin(password: Password): String
}