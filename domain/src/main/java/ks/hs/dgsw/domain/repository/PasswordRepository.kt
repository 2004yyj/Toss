package ks.hs.dgsw.domain.repository

interface PasswordRepository {
    suspend fun postPasswordRegister(pw: String): String
    suspend fun postPasswordLogin(pw: String): String
}