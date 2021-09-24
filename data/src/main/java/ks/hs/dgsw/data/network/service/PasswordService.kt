package ks.hs.dgsw.data.network.service

import ks.hs.dgsw.domain.entity.response.Response
import ks.hs.dgsw.domain.entity.response.TokenResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface PasswordService {
    @POST("/password")
    suspend fun postPasswordRegister(
        @Query("pw") pw: String
    ): retrofit2.Response<Response<Any?>>

    @POST("/password/login")
    suspend fun postPasswordLogin(
        @Query("pw") pw: String
    ): retrofit2.Response<TokenResponse>
}