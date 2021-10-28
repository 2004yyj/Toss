package ks.hs.dgsw.data.network.remote

import ks.hs.dgsw.data.base.BaseRemote
import ks.hs.dgsw.data.network.service.TransferService
import ks.hs.dgsw.domain.entity.request.GetMoney
import ks.hs.dgsw.domain.entity.request.SendMoney
import javax.inject.Inject

class TransferRemote @Inject constructor(
    override val service: TransferService
): BaseRemote<TransferService>() {
    suspend fun postSendMoney(sendMoney: SendMoney): String {
        return getMessage(service.postSendMoney(sendMoney))
    }

    suspend fun postGetMoney(getMoney: GetMoney): String {
        return getMessage(service.postGetMoney(getMoney))
    }
}