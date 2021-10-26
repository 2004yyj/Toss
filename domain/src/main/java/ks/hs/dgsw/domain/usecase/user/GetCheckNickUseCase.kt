package ks.hs.dgsw.domain.usecase.user

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Inject

class GetCheckNickUseCase @Inject constructor(
    private val userRepository: UserRepository
): ParamsUseCase<GetCheckNickUseCase.Params, Boolean>() {
    data class Params(val nick: String)

    override suspend fun buildParamsUseCase(params: Params): Boolean {
        return userRepository.getCheckNick(params.nick)
    }
}