package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.domain.repository.TransferRepository
import ks.hs.dgsw.domain.usecase.transfer.PostGetMoneyUseCase
import ks.hs.dgsw.domain.usecase.transfer.PostSendMoneyUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TransferUseCase {
    @Provides
    @Singleton
    fun providesPostSendMoneyUseCase(transferRepository: TransferRepository) =
        PostSendMoneyUseCase(transferRepository)

    @Provides
    @Singleton
    fun providesPostGetMoneyUseCase(transferRepository: TransferRepository) =
        PostGetMoneyUseCase(transferRepository)
}