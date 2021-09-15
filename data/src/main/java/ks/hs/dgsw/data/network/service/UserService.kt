package ks.hs.dgsw.data.network.service

import ks.hs.dgsw.data.entity.UserData
import ks.hs.dgsw.domain.entity.dto.User
import ks.hs.dgsw.domain.entity.request.Login
import ks.hs.dgsw.domain.entity.request.Register
import ks.hs.dgsw.domain.entity.response.Response
import ks.hs.dgsw.domain.entity.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("/api/user/register")
    suspend fun postRegister(
        @Body register: Register
    ): retrofit2.Response<Response<Any?>>

    @POST("/api/user/login")
    suspend fun postLogin(
        @Body login: Login
    ): retrofit2.Response<TokenResponse>

    @GET("/api/user")
    suspend fun getMyInfo(): retrofit2.Response<Response<UserData>>
}