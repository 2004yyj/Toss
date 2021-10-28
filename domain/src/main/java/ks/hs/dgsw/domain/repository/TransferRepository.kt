package ks.hs.dgsw.domain.repository

import ks.hs.dgsw.domain.entity.request.GetMoney
import ks.hs.dgsw.domain.entity.request.SendMoney

interface TransferRepository {
    suspend fun postSendMoney(sendMoney: SendMoney): String
    suspend fun postGetMoney(getMoney: GetMoney): String
}