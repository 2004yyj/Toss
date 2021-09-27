package ks.hs.dgsw.domain.usecase.password

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.request.Password
import ks.hs.dgsw.domain.repository.PasswordRepository
import javax.inject.Inject

class PostPasswordLoginUseCase @Inject constructor(
    private val passwordRepository: PasswordRepository
): ParamsUseCase<PostPasswordLoginUseCase.Params, String>() {

    override suspend fun buildParamsUseCase(params: Params): String {
        return passwordRepository.postPasswordLogin(params.password)
    }

    data class Params(
        val password: Password
    )
}