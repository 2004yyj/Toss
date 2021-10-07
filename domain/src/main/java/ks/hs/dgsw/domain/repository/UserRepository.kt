package ks.hs.dgsw.domain.repository

import ks.hs.dgsw.domain.entity.dto.LoginToken
import ks.hs.dgsw.domain.entity.dto.RegisterToken
import ks.hs.dgsw.domain.entity.dto.User
import ks.hs.dgsw.domain.entity.request.Login
import ks.hs.dgsw.domain.entity.request.Register

interface UserRepository {
    suspend fun postRegister(register: Register): RegisterToken
    suspend fun postLogin(login: Login): LoginToken
    suspend fun getMyInfo(): User
}