package ks.hs.dgsw.domain.usecase.password

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.LoginToken
import ks.hs.dgsw.domain.entity.request.PasswordLogin
import ks.hs.dgsw.domain.repository.PasswordRepository
import javax.inject.Inject

class PostPasswordLoginUseCase @Inject constructor(
    private val passwordRepository: PasswordRepository
): ParamsUseCase<PostPasswordLoginUseCase.Params, LoginToken>() {

    override suspend fun buildParamsUseCase(params: Params): LoginToken {
        return passwordRepository.postPasswordLogin(params.passwordLogin)
    }

    data class Params(
        val passwordLogin: PasswordLogin
    )
}