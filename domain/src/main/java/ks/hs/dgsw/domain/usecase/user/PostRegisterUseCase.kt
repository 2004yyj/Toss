package ks.hs.dgsw.domain.usecase.user

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.RegisterToken
import ks.hs.dgsw.domain.entity.request.Register
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Inject

class PostRegisterUseCase @Inject constructor(
    private val userRepository: UserRepository
): ParamsUseCase<PostRegisterUseCase.Params, RegisterToken>() {
    override suspend fun buildParamsUseCase(params: Params): RegisterToken {
        return userRepository.postRegister(params.register)
    }

    data class Params(val register: Register)
}