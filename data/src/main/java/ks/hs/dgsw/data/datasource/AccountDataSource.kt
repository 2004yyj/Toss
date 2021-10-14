package ks.hs.dgsw.data.datasource

import ks.hs.dgsw.data.base.BaseDataSource
import ks.hs.dgsw.data.mapper.toEntity
import ks.hs.dgsw.data.network.remote.AccountRemote
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.AccountNumber
import ks.hs.dgsw.domain.entity.request.PostAccount
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val remote: AccountRemote
): BaseDataSource<AccountRemote>() {
    suspend fun postAccount(postAccount: PostAccount): AccountNumber {
        return remote.postAccount(postAccount).toEntity()
    }

    suspend fun getAccountByToken(): List<Account> {
        return remote.getAccountsByToken().map {
            it.toEntity()
        }
    }

    suspend fun getAccountsByPhoneNumber(phone: String): List<Account> {
        return remote.getAccountsByPhoneNumber(phone).map {
            it.toEntity()
        }
    }
}