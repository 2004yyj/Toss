package ks.hs.dgsw.domain.repository

import ks.hs.dgsw.domain.entity.dto.Files
import okhttp3.MultipartBody

interface UploadRepository {
    suspend fun postUploadImage(images: MultipartBody.Part): Files
}