package ks.hs.dgsw.data.network.remote

import ks.hs.dgsw.data.base.BaseRemote
import ks.hs.dgsw.data.entity.AccountData
import ks.hs.dgsw.data.entity.AccountNumberData
import ks.hs.dgsw.data.network.service.AccountService
import ks.hs.dgsw.domain.entity.request.PostAccount
import javax.inject.Inject

class AccountRemote @Inject constructor(
    override val service: AccountService
): BaseRemote<AccountService>() {
    suspend fun postAccount(postAccount: PostAccount): AccountNumberData {
        return getResponse(service.postAccount(postAccount))
    }

    suspend fun getAccountsByToken(): List<AccountData> {
        return getResponse(service.getAccountsByToken())
    }

    suspend fun getAccountsByPhoneNumber(phone: String): List<AccountData> {
        return getResponse(service.getAccountsByPhoneNumber(phone))
    }
}