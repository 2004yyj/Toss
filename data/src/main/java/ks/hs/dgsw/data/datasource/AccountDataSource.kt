package ks.hs.dgsw.data.datasource

import ks.hs.dgsw.data.base.BaseDataSource
import ks.hs.dgsw.data.mapper.toEntity
import ks.hs.dgsw.data.network.remote.AccountRemote
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.AccountNumber
import ks.hs.dgsw.domain.entity.dto.BaseAccount
import ks.hs.dgsw.domain.entity.request.PostAccount
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val remote: AccountRemote
): BaseDataSource<AccountRemote>() {
    suspend fun postAccount(postAccount: PostAccount): AccountNumber {
        return remote.postAccount(postAccount).toEntity()
    }

    suspend fun getAccountByToken(): BaseAccount {
        return remote.getAccountsByToken().toEntity()
    }

    suspend fun getAccountsByPhoneNumber(phone: String): BaseAccount {
        return remote.getAccountsByPhoneNumber(phone).toEntity()
    }

    suspend fun getAccountByAccountNumber(account: String): Account {
        return remote.getAccountByAccountNumber(account).toEntity()
    }
}