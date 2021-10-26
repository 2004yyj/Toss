package ks.hs.dgsw.domain.usecase.user

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Inject

class GetCheckIdUseCase @Inject constructor(
    private val userRepository: UserRepository
): ParamsUseCase<GetCheckIdUseCase.Params, Boolean>() {
    data class Params(val id: String)

    override suspend fun buildParamsUseCase(params: Params): Boolean {
        return userRepository.getCheckId(params.id)
    }
}