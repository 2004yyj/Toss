package ks.hs.dgsw.domain.usecase.account

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.PostAccountResponse
import ks.hs.dgsw.domain.entity.request.PostAccount
import ks.hs.dgsw.domain.repository.AccountRepository
import javax.inject.Inject

class PostAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
): ParamsUseCase<PostAccountUseCase.Params, PostAccountResponse>() {

    override suspend fun buildParamsUseCase(params: Params): PostAccountResponse {
        return accountRepository.postAccount(params.postAccount)
    }

    data class Params (
        val postAccount: PostAccount
    )
}