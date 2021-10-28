package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.data.datasource.AccountDataSource
import ks.hs.dgsw.data.datasource.PasswordDataSource
import ks.hs.dgsw.data.datasource.TransferDataSource
import ks.hs.dgsw.data.datasource.UserDataSource
import ks.hs.dgsw.data.repository.AccountRepositoryImpl
import ks.hs.dgsw.data.repository.PasswordRepositoryImpl
import ks.hs.dgsw.data.repository.TransferRepositoryImpl
import ks.hs.dgsw.data.repository.UserRepositoryImpl
import ks.hs.dgsw.domain.repository.AccountRepository
import ks.hs.dgsw.domain.repository.PasswordRepository
import ks.hs.dgsw.domain.repository.TransferRepository
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(userDataSource: UserDataSource): UserRepository =
        UserRepositoryImpl(userDataSource)

    @Singleton
    @Provides
    fun providePasswordRepository(passwordDataSource: PasswordDataSource): PasswordRepository =
        PasswordRepositoryImpl(passwordDataSource)

    @Singleton
    @Provides
    fun provideAccountRepository(accountDataSource: AccountDataSource): AccountRepository =
        AccountRepositoryImpl(accountDataSource)

    @Singleton
    @Provides
    fun provideTransferRepository(transferDataSource: TransferDataSource): TransferRepository =
        TransferRepositoryImpl(transferDataSource)
}