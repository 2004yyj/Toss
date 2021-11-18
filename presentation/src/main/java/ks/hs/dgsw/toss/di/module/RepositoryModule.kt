package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.data.datasource.*
import ks.hs.dgsw.data.repository.*
import ks.hs.dgsw.domain.repository.*
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

    @Singleton
    @Provides
    fun provideUploadRepository(uploadDataSource: UploadDataSource): UploadRepository =
        UploadRepositoryImpl(uploadDataSource)
}