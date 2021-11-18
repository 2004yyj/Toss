package ks.hs.dgsw.data.repository

import ks.hs.dgsw.data.datasource.UploadDataSource
import ks.hs.dgsw.domain.entity.dto.Files
import ks.hs.dgsw.domain.repository.UploadRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(
    private val uploadDataSource: UploadDataSource
): UploadRepository {
    override suspend fun postUploadImage(images: MultipartBody.Part): Files {
        return uploadDataSource.postUploadImage(images)
    }
}