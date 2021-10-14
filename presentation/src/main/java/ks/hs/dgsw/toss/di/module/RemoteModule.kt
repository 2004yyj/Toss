package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.data.network.remote.AccountRemote
import ks.hs.dgsw.data.network.remote.PasswordRemote
import ks.hs.dgsw.data.network.remote.UserRemote
import ks.hs.dgsw.data.network.service.AccountService
import ks.hs.dgsw.data.network.service.PasswordService
import ks.hs.dgsw.data.network.service.UserService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideUserRemote(service: UserService) =
        UserRemote(service)

    @Singleton
    @Provides
    fun providePasswordRemote(service: PasswordService) =
        PasswordRemote(service)

    @Singleton
    @Provides
    fun provideAccountRemote(service: AccountService) =
        AccountRemote(service)
}