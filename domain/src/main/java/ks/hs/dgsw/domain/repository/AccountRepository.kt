package ks.hs.dgsw.domain.repository

import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.PostAccountResponse
import ks.hs.dgsw.domain.entity.dto.BaseAccount
import ks.hs.dgsw.domain.entity.request.PostAccount

interface AccountRepository {
    suspend fun postAccount(postAccount: PostAccount): PostAccountResponse
    suspend fun getAccountsByToken(): BaseAccount
    suspend fun getAccountsByPhoneNumber(phone: String): BaseAccount
    suspend fun getAccountByAccountNumber(bankCode: Int, account: String): Account
}