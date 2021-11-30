package ks.hs.dgsw.domain.repository

import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.PostAccountResponse
import ks.hs.dgsw.domain.entity.dto.BaseAccount
import ks.hs.dgsw.domain.entity.request.PostAccount
import ks.hs.dgsw.domain.entity.request.PostAddOtherAccount

interface AccountRepository {
    suspend fun postAccount(postAccount: PostAccount): PostAccountResponse
    suspend fun getAccountsByToken(): BaseAccount
    suspend fun getAccountsByPhoneNumber(phone: String): BaseAccount
    suspend fun getAccountByAccountNumber(account: String): Account
    suspend fun postAddOtherAccount(postAddOtherAccount: PostAddOtherAccount): String
    suspend fun getOtherAccountForBank(bank: Int, account: String): Account
    suspend fun getOtherAccounts(birth: String, name: String): BaseAccount
}