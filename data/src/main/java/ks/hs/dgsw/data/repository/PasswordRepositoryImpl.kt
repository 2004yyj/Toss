package ks.hs.dgsw.data.repository

import ks.hs.dgsw.data.datasource.PasswordDataSource
import ks.hs.dgsw.domain.entity.dto.LoginToken
import ks.hs.dgsw.domain.entity.request.PasswordLogin
import ks.hs.dgsw.domain.entity.request.PasswordRegister
import ks.hs.dgsw.domain.repository.PasswordRepository
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(
    private val passwordDataSource: PasswordDataSource
): PasswordRepository {
    override suspend fun postPasswordRegister(passwordLogin: PasswordRegister): String {
        return passwordDataSource.postPasswordRegister(passwordLogin)
    }

    override suspend fun postPasswordLogin(passwordLogin: PasswordLogin): LoginToken {
        return passwordDataSource.postPasswordLogin(passwordLogin)
    }
}