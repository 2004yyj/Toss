package ks.hs.dgsw.domain.usecase.user

import ks.hs.dgsw.domain.base.BaseUseCase
import ks.hs.dgsw.domain.entity.dto.User
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Inject

class GetMyInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<User>() {
    override suspend fun buildUseCase(): User {
        return userRepository.getMyInfo()
    }
}