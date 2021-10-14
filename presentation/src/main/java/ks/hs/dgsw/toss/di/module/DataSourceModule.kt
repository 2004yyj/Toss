package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.data.datasource.AccountDataSource
import ks.hs.dgsw.data.datasource.PasswordDataSource
import ks.hs.dgsw.data.datasource.UserDataSource
import ks.hs.dgsw.data.network.remote.AccountRemote
import ks.hs.dgsw.data.network.remote.PasswordRemote
import ks.hs.dgsw.data.network.remote.UserRemote
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideUserDataSource(remote: UserRemote) =
        UserDataSource(remote)

    @Singleton
    @Provides
    fun providePasswordDataSource(remote: PasswordRemote) =
        PasswordDataSource(remote)

    @Singleton
    @Provides
    fun provideAccountDataSource(remote: AccountRemote) =
        AccountDataSource(remote)
}