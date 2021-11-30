package ks.hs.dgsw.data.repository

import ks.hs.dgsw.data.datasource.AccountDataSource
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.PostAccountResponse
import ks.hs.dgsw.domain.entity.dto.BaseAccount
import ks.hs.dgsw.domain.entity.request.PostAccount
import ks.hs.dgsw.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountDataSource: AccountDataSource
): AccountRepository {
    override suspend fun postAccount(postAccount: PostAccount): PostAccountResponse {
        return accountDataSource.postAccount(postAccount)
    }

    override suspend fun getAccountsByToken(): BaseAccount {
        return accountDataSource.getAccountByToken()
    }

    override suspend fun getAccountsByPhoneNumber(phone: String): BaseAccount {
        return accountDataSource.getAccountsByPhoneNumber(phone)
    }

    override suspend fun getAccountByAccountNumber(bankCode: Int, account: String): Account {
        return accountDataSource.getAccountByAccountNumber(bankCode, account)
    }
}