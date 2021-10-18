package ks.hs.dgsw.data.repository

import ks.hs.dgsw.data.datasource.AccountDataSource
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.AccountNumber
import ks.hs.dgsw.domain.entity.request.PostAccount
import ks.hs.dgsw.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountDataSource: AccountDataSource
): AccountRepository {
    override suspend fun postAccount(postAccount: PostAccount): AccountNumber {
        return accountDataSource.postAccount(postAccount)
    }

    override suspend fun getAccountsByToken(): List<Account> {
        return accountDataSource.getAccountByToken()
    }

    override suspend fun getAccountsByPhoneNumber(phone: String): List<Account> {
        return accountDataSource.getAccountsByPhoneNumber(phone)
    }

    override suspend fun getAccountByAccountNumber(account: String): Account {
        return accountDataSource.getAccountByAccountNumber(account)
    }
}