package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.domain.repository.UserRepository
import ks.hs.dgsw.domain.usecase.user.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserUseCaseModule {
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

    @Singleton
    @Provides
    fun provideGetInfoByBirthAndNameUseCase(userRepository: UserRepository) =
        GetInfoByBirthAndNameUseCase(userRepository)

    @Singleton
    @Provides
    fun provideGetCheckIdUseCase(userRepository: UserRepository) =
        GetCheckIdUseCase(userRepository)

    @Singleton
    @Provides
    fun provideGetCheckNickUseCase(userRepository: UserRepository) =
        GetCheckNickUseCase(userRepository)
}