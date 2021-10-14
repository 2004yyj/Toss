package ks.hs.dgsw.domain.repository

import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.AccountNumber
import ks.hs.dgsw.domain.entity.request.PostAccount

interface AccountRepository {
    suspend fun postAccount(postAccount: PostAccount): AccountNumber
    suspend fun getAccountsByToken(): List<Account>
    suspend fun getAccountsByPhoneNumber(phone: String): List<Account>
}