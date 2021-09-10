package ks.hs.dgsw.domain.usecase.user

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.request.Register
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Inject

class PostRegisterUseCase @Inject constructor(
    private val userRepository: UserRepository
): ParamsUseCase<PostRegisterUseCase.Params, String>() {
    override suspend fun buildParamsUseCase(params: Params): String {
        return userRepository.postRegister(params.register)
    }

    data class Params(val register: Register)
}