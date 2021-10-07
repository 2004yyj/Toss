package ks.hs.dgsw.data.network.service

import ks.hs.dgsw.data.entity.LoginTokenData
import ks.hs.dgsw.domain.entity.request.PasswordLogin
import ks.hs.dgsw.domain.entity.request.PasswordRegister
import ks.hs.dgsw.domain.entity.response.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PasswordService {
    @POST("/password")
    suspend fun postPasswordRegister(
        @Body passwordLogin: PasswordRegister
    ): retrofit2.Response<Response<Any?>>

    @POST("/password/login")
    suspend fun postPasswordLogin(
        @Body passwordLogin: PasswordLogin
    ): retrofit2.Response<Response<LoginTokenData>>
}