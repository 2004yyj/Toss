package ks.hs.dgsw.domain.usecase.account

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.repository.AccountRepository
import javax.inject.Inject

class GetAccountByAccountNumberUseCase @Inject constructor(
    private val accountRepository: AccountRepository
): ParamsUseCase<GetAccountByAccountNumberUseCase.Params, Account>() {
    data class Params(val account: String)

    override suspend fun buildParamsUseCase(params: Params): Account {
        return accountRepository.getAccountByAccountNumber(params.account)
    }
}