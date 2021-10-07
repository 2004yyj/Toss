package ks.hs.dgsw.data.repository

import ks.hs.dgsw.data.datasource.UserDataSource
import ks.hs.dgsw.domain.entity.dto.LoginToken
import ks.hs.dgsw.domain.entity.dto.RegisterToken
import ks.hs.dgsw.domain.entity.dto.User
import ks.hs.dgsw.domain.entity.request.Login
import ks.hs.dgsw.domain.entity.request.Register
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserRepository {
    override suspend fun postRegister(register: Register): RegisterToken {
        return userDataSource.postRegister(register)
    }

    override suspend fun postLogin(login: Login): LoginToken {
        return userDataSource.postLogin(login)
    }

    override suspend fun getMyInfo(): User {
        return userDataSource.getMyInfo()
    }
}