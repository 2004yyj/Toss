package ks.hs.dgsw.data.network.service

import ks.hs.dgsw.data.entity.AccountData
import ks.hs.dgsw.data.entity.AccountNumberData
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.request.PostAccount
import ks.hs.dgsw.domain.entity.response.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountService {
    @POST("/account")
    suspend fun postAccount(
        @Body postAccount: PostAccount
    ): retrofit2.Response<Response<AccountNumberData>>

    @GET("/account")
    suspend fun getAccountsByToken(): retrofit2.Response<Response<List<AccountData>>>

    @GET("/account/{phone}")
    suspend fun getAccountsByPhoneNumber(
        @Path("phone") phone: String
    ): retrofit2.Response<Response<List<AccountData>>>

    @GET("/account/acount/{account}")
    suspend fun getAccountByAccountNumber(
        @Path("account") account: String
    ): retrofit2.Response<Response<AccountData>>
}