package ks.hs.dgsw.domain.usecase.upload

import ks.hs.dgsw.domain.base.ParamsUseCase
import ks.hs.dgsw.domain.entity.dto.Files
import ks.hs.dgsw.domain.repository.UploadRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class PostUploadImageUseCase @Inject constructor(
    private val uploadRepository: UploadRepository
): ParamsUseCase<PostUploadImageUseCase.Params, Files>() {

    data class Params(
        val images: MultipartBody.Part
    )

    override suspend fun buildParamsUseCase(params: Params): Files {
        return uploadRepository.postUploadImage(params.images)
    }

}