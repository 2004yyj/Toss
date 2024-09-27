package ks.hs.dgsw.domain.usecase.account

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.BaseAccount
import ks.hs.dgsw.domain.repository.AccountRepository
import javax.inject.Inject

class GetOtherAccountsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
): ParamsUseCase<GetOtherAccountsUseCase.Params, List<Account>>() {
    override suspend fun buildParamsUseCase(params: Params): List<Account> {
        return accountRepository.getOtherAccounts(params.birth, params.name)
    }

    data class Params(
        val birth: String,
        val name: String,
    )
}