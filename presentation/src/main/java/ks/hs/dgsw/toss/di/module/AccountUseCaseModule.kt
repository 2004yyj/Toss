package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.domain.repository.AccountRepository
import ks.hs.dgsw.domain.usecase.account.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountUseCaseModule {
    @Singleton
    @Provides
    fun providesPostAccountUseCase(accountRepository: AccountRepository) =
        PostAccountUseCase(accountRepository)

    @Singleton
    @Provides
    fun providesGetAccountsByTokenUseCase(accountRepository: AccountRepository) =
        GetAccountsByTokenUseCase(accountRepository)

    @Singleton
    @Provides
    fun providesGetAccountsByPhoneNumberUseCase(accountRepository: AccountRepository) =
        GetAccountsByPhoneNumberUseCase(accountRepository)

    @Singleton
    @Provides
    fun providesGetAccountByAccountNumberUseCase(accountRepository: AccountRepository) =
        GetAccountByAccountNumberUseCase(accountRepository)

    @Singleton
    @Provides
    fun providesPostAddOtherAccountUseCase(accountRepository: AccountRepository) =
        PostAddOtherAccountUseCase(accountRepository)

    @Singleton
    @Provides
    fun providesGetOtherAccountForBankUseCase(accountRepository: AccountRepository) =
        GetOtherAccountForBankUseCase(accountRepository)

    @Singleton
    @Provides
    fun providesGetOtherAccountsUseCase(accountRepository: AccountRepository) =
        GetOtherAccountsUseCase(accountRepository)
}