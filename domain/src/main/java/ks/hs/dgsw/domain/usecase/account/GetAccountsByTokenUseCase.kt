package ks.hs.dgsw.domain.usecase.account

import ks.hs.dgsw.domain.base.BaseUseCase
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.BaseAccount
import ks.hs.dgsw.domain.repository.AccountRepository
import javax.inject.Inject

class GetAccountsByTokenUseCase @Inject constructor(
    private val accountRepository: AccountRepository
): BaseUseCase<BaseAccount>() {
    override suspend fun buildUseCase(): BaseAccount {
        return accountRepository.getAccountsByToken()
    }
}