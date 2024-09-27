package ks.hs.dgsw.data.datasource

import ks.hs.dgsw.data.base.BaseDataSource
import ks.hs.dgsw.data.mapper.toEntity
import ks.hs.dgsw.data.network.remote.AccountRemote
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.PostAccountResponse
import ks.hs.dgsw.domain.entity.dto.BaseAccount
import ks.hs.dgsw.domain.entity.request.PostAccount
import ks.hs.dgsw.domain.entity.request.PostAddOtherAccount
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val remote: AccountRemote
): BaseDataSource<AccountRemote>() {
    suspend fun postAccount(postAccount: PostAccount): PostAccountResponse {
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

    suspend fun postAddOtherAccount(postAddOtherAccount: PostAddOtherAccount): String {
        return remote.postAddOtherAccount(postAddOtherAccount)
    }

    suspend fun getOtherAccountForBank(bank: Int, account: String): Account {
        return remote.getOtherAccountForBank(bank, account).toEntity()
    }

    suspend fun getOtherAccounts(birth: String, name: String): List<Account> {
        return remote.getOtherAccounts(birth, name).map {
            it.toEntity()
        }
    }
}