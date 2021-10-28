package ks.hs.dgsw.data.repository

import ks.hs.dgsw.data.datasource.TransferDataSource
import ks.hs.dgsw.domain.entity.request.GetMoney
import ks.hs.dgsw.domain.entity.request.SendMoney
import ks.hs.dgsw.domain.repository.TransferRepository
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val transferDataSource: TransferDataSource
): TransferRepository {
    override suspend fun postSendMoney(sendMoney: SendMoney): String {
        return transferDataSource.postSendMoney(sendMoney)
    }

    override suspend fun postGetMoney(getMoney: GetMoney): String {
        return transferDataSource.postGetMoney(getMoney)
    }
}