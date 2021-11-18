package ks.hs.dgsw.data.network.service

import ks.hs.dgsw.data.entity.FilesData
import ks.hs.dgsw.domain.entity.response.Response
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadService {
    @Multipart
    @POST("/upload")
    suspend fun postUploadImage(@Part images: MultipartBody.Part): retrofit2.Response<Response<FilesData>>
}