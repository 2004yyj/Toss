package ks.hs.dgsw.toss.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.data.datasource.UserDataSource
import ks.hs.dgsw.data.repository.UserRepositoryImpl
import ks.hs.dgsw.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(userDataSource: UserDataSource) =
        UserRepositoryImpl(userDataSource)
}