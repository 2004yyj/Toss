package ks.hs.dgsw.data.network.remote

import ks.hs.dgsw.data.base.BaseRemote
import ks.hs.dgsw.data.entity.FilesData
import ks.hs.dgsw.data.network.service.UploadService
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadRemote @Inject constructor(
    override val service: UploadService
): BaseRemote<UploadService>() {
    suspend fun postUploadImage(images: MultipartBody.Part): FilesData {
        return getResponse(service.postUploadImage(images))
    }
}