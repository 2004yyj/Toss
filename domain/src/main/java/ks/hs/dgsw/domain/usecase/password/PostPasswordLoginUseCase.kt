package ks.hs.dgsw.domain.usecase.password

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.Token
import ks.hs.dgsw.domain.entity.request.Password
import ks.hs.dgsw.domain.repository.PasswordRepository
import javax.inject.Inject

class PostPasswordLoginUseCase @Inject constructor(
    private val passwordRepository: PasswordRepository
): ParamsUseCase<PostPasswordLoginUseCase.Params, Token>() {

    override suspend fun buildParamsUseCase(params: Params): Token {
        return passwordRepository.postPasswordLogin(params.password)
    }

    data class Params(
        val password: Password
    )
}