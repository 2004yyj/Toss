package ks.hs.dgsw.data.network.remote

import ks.hs.dgsw.data.base.BaseRemote
import ks.hs.dgsw.data.entity.AccountData
import ks.hs.dgsw.data.entity.PostAccountResponseData
import ks.hs.dgsw.data.entity.BaseAccountData
import ks.hs.dgsw.data.network.service.AccountService
import ks.hs.dgsw.domain.entity.request.PostAccount
import ks.hs.dgsw.domain.entity.request.PostAddOtherAccount
import javax.inject.Inject

class AccountRemote @Inject constructor(
    override val service: AccountService
): BaseRemote<AccountService>() {
    suspend fun postAccount(postAccount: PostAccount): PostAccountResponseData {
        return getResponse(service.postAccount(postAccount))
    }

    suspend fun getAccountsByToken(): BaseAccountData {
        return getResponse(service.getAccountsByToken())
    }

    suspend fun getAccountsByPhoneNumber(phone: String): BaseAccountData {
        return getResponse(service.getAccountsByPhoneNumber(phone))
    }

    suspend fun getAccountByAccountNumber(account: String): AccountData {
        return getResponse(service.getAccountByAccountNumber(account))
    }

    suspend fun postAddOtherAccount(postAddOtherAccount: PostAddOtherAccount): String {
        return getMessage(service.postAddOtherAccount(postAddOtherAccount))
    }

    suspend fun getOtherAccountForBank(bank: Int, account: String): AccountData {
        return getResponse(service.getOtherAccountForBank(bank, account))
    }

    suspend fun getOtherAccounts(birth: String, name: String): List<AccountData> {
        return getResponse(service.getOtherAccounts(birth, name))
    }
}