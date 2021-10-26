package ks.hs.dgsw.domain.usecase.user

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.User
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Inject

class GetInfoByBirthAndNameUseCase @Inject constructor(
    private val userRepository: UserRepository
): ParamsUseCase<GetInfoByBirthAndNameUseCase.Params, User>() {
    data class Params(
        val birth: String,
        val name: String
    )

    override suspend fun buildParamsUseCase(params: Params): User {
        return userRepository.getInfoByBirthAndName(params.birth, params.name)
    }
}