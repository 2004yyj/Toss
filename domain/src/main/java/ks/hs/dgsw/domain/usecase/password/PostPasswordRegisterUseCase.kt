package ks.hs.dgsw.domain.usecase.password

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.repository.PasswordRepository
import javax.inject.Inject

class PostPasswordRegisterUseCase @Inject constructor(
    private val passwordRepository: PasswordRepository
): ParamsUseCase<PostPasswordRegisterUseCase.Params, String>() {

    override suspend fun buildParamsUseCase(params: Params): String {
        return passwordRepository.postPasswordRegister(params.pw)
    }

    data class Params(
        val pw: String
    )
}