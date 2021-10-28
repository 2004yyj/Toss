package ks.hs.dgsw.data.datasource

import ks.hs.dgsw.data.base.BaseDataSource
import ks.hs.dgsw.data.network.remote.TransferRemote
import ks.hs.dgsw.domain.entity.request.GetMoney
import ks.hs.dgsw.domain.entity.request.SendMoney
import javax.inject.Inject

class TransferDataSource @Inject constructor(
    override val remote: TransferRemote
): BaseDataSource<TransferRemote>() {
    suspend fun postSendMoney(sendMoney: SendMoney): String {
        return remote.postSendMoney(sendMoney)
    }

    suspend fun postGetMoney(getMoney: GetMoney): String {
        return remote.postGetMoney(getMoney)
    }
}