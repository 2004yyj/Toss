package ks.hs.dgsw.data.repository

import ks.hs.dgsw.data.datasource.PasswordDataSource
import ks.hs.dgsw.domain.repository.PasswordRepository
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(
    private val passwordDataSource: PasswordDataSource
): PasswordRepository {
    override suspend fun postPasswordRegister(pw: String): String {
        return passwordDataSource.postPasswordRegister(pw)
    }

    override suspend fun postPasswordLogin(pw: String): String {
        return passwordDataSource.postPasswordLogin(pw)
    }
}