package ks.hs.dgsw.data.network.service

import ks.hs.dgsw.data.entity.LoginTokenData
import ks.hs.dgsw.data.entity.RegisterTokenData
import ks.hs.dgsw.data.entity.UserData
import ks.hs.dgsw.domain.entity.request.Login
import ks.hs.dgsw.domain.entity.request.Register
import ks.hs.dgsw.domain.entity.response.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("/user/register")
    suspend fun postRegister(
        @Body register: Register
    ): retrofit2.Response<Response<RegisterTokenData>>

    @POST("/user/login")
    suspend fun postLogin(
        @Body login: Login
    ): retrofit2.Response<Response<LoginTokenData>>

    @GET("/user")
    suspend fun getMyInfo(): retrofit2.Response<Response<UserData>>

    @GET("/user/get")
    suspend fun getInfoByBirthAndName(
        @Query("birth") birth: String,
        @Query("name") name: String
    ): retrofit2.Response<Response<UserData>>

    @GET("/user/check-id")
    suspend fun getCheckId(@Query("id") id: String): retrofit2.Response<Response<Boolean>>

    @GET("/user/check-nick")
    suspend fun getCheckNick(@Query("nick") nick: String): retrofit2.Response<Response<Boolean>>

}