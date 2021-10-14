package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.domain.repository.PasswordRepository
import ks.hs.dgsw.domain.usecase.password.PostPasswordLoginUseCase
import ks.hs.dgsw.domain.usecase.password.PostPasswordRegisterUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PasswordUseCaseModule {
    @Singleton
    @Provides
    fun providePostPasswordRegisterUseCase(passwordRepository: PasswordRepository) =
        PostPasswordRegisterUseCase(passwordRepository)

    @Singleton
    @Provides
    fun providePostPasswordLoginUseCase(passwordRepository: PasswordRepository) =
        PostPasswordLoginUseCase(passwordRepository)
}