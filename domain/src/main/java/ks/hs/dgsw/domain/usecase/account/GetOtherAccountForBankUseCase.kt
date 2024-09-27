package ks.hs.dgsw.domain.usecase.account

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.repository.AccountRepository
import javax.inject.Inject

class GetOtherAccountForBankUseCase @Inject constructor(
    private val accountRepository: AccountRepository
): ParamsUseCase<GetOtherAccountForBankUseCase.Params, Account>() {
    override suspend fun buildParamsUseCase(params: Params): Account {
        return accountRepository.getOtherAccountForBank(params.bank, params.account)
    }

    data class Params(
        val bank: Int,
        val account: String
    )
}