package ks.hs.dgsw.data.network.service

import ks.hs.dgsw.data.entity.AccountData
import ks.hs.dgsw.data.entity.PostAccountResponseData
import ks.hs.dgsw.data.entity.BaseAccountData
import ks.hs.dgsw.domain.entity.request.PostAccount
import ks.hs.dgsw.domain.entity.request.PostAddOtherAccount
import ks.hs.dgsw.domain.entity.response.Response
import retrofit2.http.*

interface AccountService {
    @POST("/account")
    suspend fun postAccount(
        @Body postAccount: PostAccount
    ): retrofit2.Response<Response<PostAccountResponseData>>

    @GET("/account")
    suspend fun getAccountsByToken(): retrofit2.Response<Response<BaseAccountData>>

    @GET("/account/get/{phone}")
    suspend fun getAccountsByPhoneNumber(
        @Path("phone") phone: String
    ): retrofit2.Response<Response<BaseAccountData>>

    @GET("/account/{account}")
    suspend fun getAccountByAccountNumber(
        @Path("account") account: String
    ): retrofit2.Response<Response<AccountData>>

    @POST("/account/add")
    suspend fun postAddOtherAccount(
        @Body postAddOtherAccount: PostAddOtherAccount
    ): retrofit2.Response<Response<Any?>>

    @GET("/account/other2/{bank}")
    suspend fun getOtherAccountForBank(
        @Path("bank") bank: Int,
        @Query("account") account: String
    ): retrofit2.Response<Response<AccountData>>

    @GET("/account/other")
    suspend fun getOtherAccounts(
        @Query("birth") birth: String,
        @Query("name") name: String
    ): retrofit2.Response<Response<BaseAccountData>>
}