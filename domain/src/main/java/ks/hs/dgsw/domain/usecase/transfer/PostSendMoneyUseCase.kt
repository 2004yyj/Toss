package ks.hs.dgsw.domain.usecase.transfer

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.request.SendMoney
import ks.hs.dgsw.domain.repository.TransferRepository
import javax.inject.Inject

class PostSendMoneyUseCase @Inject constructor(
    private val transferRepository: TransferRepository
): ParamsUseCase<PostSendMoneyUseCase.Params, String>() {
    data class Params(val sendMoney: SendMoney)

    override suspend fun buildParamsUseCase(params: Params): String {
        return transferRepository.postSendMoney(params.sendMoney)
    }
}