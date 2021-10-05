package ks.hs.dgsw.domain.usecase.user

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.Token
import ks.hs.dgsw.domain.entity.request.Login
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
): ParamsUseCase<PostLoginUseCase.Params, Token>() {
    override suspend fun buildParamsUseCase(params: Params): Token {
        return userRepository.postLogin(params.login)
    }

    data class Params(val login: Login)
}