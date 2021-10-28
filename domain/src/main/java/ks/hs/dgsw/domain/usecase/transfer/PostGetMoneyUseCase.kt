package ks.hs.dgsw.domain.usecase.transfer

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.request.GetMoney
import ks.hs.dgsw.domain.repository.TransferRepository
import javax.inject.Inject

class PostGetMoneyUseCase @Inject constructor(
    private val transferRepository: TransferRepository
): ParamsUseCase<PostGetMoneyUseCase.Params, String>() {
    data class Params(val getMoney: GetMoney)

    override suspend fun buildParamsUseCase(params: Params): String {
        return transferRepository.postGetMoney(params.getMoney)
    }
}