package ks.hs.dgsw.domain.usecase.account

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.request.PostAddOtherAccount
import ks.hs.dgsw.domain.repository.AccountRepository
import javax.inject.Inject

class PostAddOtherAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
): ParamsUseCase<PostAddOtherAccountUseCase.Params, String>() {
    override suspend fun buildParamsUseCase(params: Params): String {
        return accountRepository.postAddOtherAccount(params.postAddOtherAccount)
    }

    data class Params(val postAddOtherAccount: PostAddOtherAccount)
}