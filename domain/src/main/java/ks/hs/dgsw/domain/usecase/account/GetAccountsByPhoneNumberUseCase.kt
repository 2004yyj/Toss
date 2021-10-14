package ks.hs.dgsw.domain.usecase.account

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.repository.AccountRepository
import javax.inject.Inject

class GetAccountsByPhoneNumberUseCase @Inject constructor(
    private val accountRepository: AccountRepository
): ParamsUseCase<GetAccountsByPhoneNumberUseCase.Params, List<Account>>() {
    override suspend fun buildParamsUseCase(params: Params): List<Account> {
        return accountRepository.getAccountsByPhoneNumber(params.phone)
    }

    data class Params(
        val phone: String
    )
}