package ks.hs.dgsw.data.datasource

import ks.hs.dgsw.data.base.BaseDataSource
import ks.hs.dgsw.data.mapper.toEntity
import ks.hs.dgsw.data.network.remote.UploadRemote
import ks.hs.dgsw.domain.entity.dto.Files
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadDataSource @Inject constructor(
    override val remote: UploadRemote
): BaseDataSource<UploadRemote>() {
    suspend fun postUploadImage(images: MultipartBody.Part): Files {
        return remote.postUploadImage(images).toEntity()
    }
}