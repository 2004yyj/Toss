package ks.hs.dgsw.domain.repository

import ks.hs.dgsw.domain.entity.dto.LoginToken
import ks.hs.dgsw.domain.entity.request.PasswordLogin
import ks.hs.dgsw.domain.entity.request.PasswordRegister

interface PasswordRepository {
    suspend fun postPasswordRegister(passwordLogin: PasswordRegister): String
    suspend fun postPasswordLogin(passwordLogin: PasswordLogin): LoginToken
}