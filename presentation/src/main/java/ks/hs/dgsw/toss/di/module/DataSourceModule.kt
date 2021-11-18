package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.data.datasource.*
import ks.hs.dgsw.data.network.remote.*
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

    @Singleton
    @Provides
    fun provideTransferDataSource(remote: TransferRemote) =
        TransferDataSource(remote)

    @Singleton
    @Provides
    fun provideUploadDataSource(remote: UploadRemote) =
        UploadDataSource(remote)
}