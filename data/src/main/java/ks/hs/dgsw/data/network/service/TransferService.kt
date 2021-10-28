package ks.hs.dgsw.data.network.service

import ks.hs.dgsw.domain.entity.request.GetMoney
import ks.hs.dgsw.domain.entity.request.SendMoney
import ks.hs.dgsw.domain.entity.response.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TransferService {
    @POST("/transfer/send")
    suspend fun postSendMoney(
        @Body sendMoney: SendMoney
    ): retrofit2.Response<Response<Any?>>


    @POST("/transfer/get")
    suspend fun postGetMoney(
        @Body getMoney: GetMoney
    ): retrofit2.Response<Response<Any?>>
}