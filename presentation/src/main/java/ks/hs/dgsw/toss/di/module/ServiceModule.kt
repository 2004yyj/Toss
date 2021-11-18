package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.data.network.service.*
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun providePasswordService(retrofit: Retrofit): PasswordService =
        retrofit.create(PasswordService::class.java)

    @Singleton
    @Provides
    fun provideAccountService(retrofit: Retrofit): AccountService =
        retrofit.create(AccountService::class.java)

    @Singleton
    @Provides
    fun provideTransferService(retrofit: Retrofit): TransferService =
        retrofit.create(TransferService::class.java)

    @Singleton
    @Provides
    fun provideUploadService(retrofit: Retrofit): UploadService =
        retrofit.create(UploadService::class.java)
}