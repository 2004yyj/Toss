package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.domain.repository.UserRepository
import ks.hs.dgsw.domain.usecase.user.GetMyInfoUseCase
import ks.hs.dgsw.domain.usecase.user.PostLoginUseCase
import ks.hs.dgsw.domain.usecase.user.PostRegisterUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun providePostRegisterUseCase(userRepository: UserRepository) =
        PostRegisterUseCase(userRepository)

    @Singleton
    @Provides
    fun providePostLoginUseCase(userRepository: UserRepository) =
        PostLoginUseCase(userRepository)

    @Singleton
    @Provides
    fun provideGetMyInfoUseCase(userRepository: UserRepository) =
        GetMyInfoUseCase(userRepository)
}