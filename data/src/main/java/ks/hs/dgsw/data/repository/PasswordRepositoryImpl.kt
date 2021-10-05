package ks.hs.dgsw.data.repository

import ks.hs.dgsw.data.datasource.PasswordDataSource
import ks.hs.dgsw.domain.entity.dto.Token
import ks.hs.dgsw.domain.entity.request.Password
import ks.hs.dgsw.domain.repository.PasswordRepository
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(
    private val passwordDataSource: PasswordDataSource
): PasswordRepository {
    override suspend fun postPasswordRegister(password: Password): String {
        return passwordDataSource.postPasswordRegister(password)
    }

    override suspend fun postPasswordLogin(password: Password): Token {
        return passwordDataSource.postPasswordLogin(password)
    }
}