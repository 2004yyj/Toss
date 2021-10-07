package ks.hs.dgsw.domain.usecase.user

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.LoginToken
import ks.hs.dgsw.domain.entity.request.Login
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
): ParamsUseCase<PostLoginUseCase.Params, LoginToken>() {
    override suspend fun buildParamsUseCase(params: Params): LoginToken {
        return userRepository.postLogin(params.login)
    }

    data class Params(val login: Login)
}