package ks.hs.dgsw.toss.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ks.hs.dgsw.domain.repository.UploadRepository
import ks.hs.dgsw.domain.usecase.upload.PostUploadImageUseCase

@Module
@InstallIn(SingletonComponent::class)
object UploadUseCaseModule {
    fun providePostUploadImageUseCase(uploadRepository: UploadRepository) =
        PostUploadImageUseCase(uploadRepository)
}