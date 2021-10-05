package ks.hs.dgsw.data.datasource

import ks.hs.dgsw.data.base.BaseDataSource
import ks.hs.dgsw.data.entity.TokenData
import ks.hs.dgsw.data.mapper.toEntity
import ks.hs.dgsw.data.network.remote.PasswordRemote
import ks.hs.dgsw.domain.entity.dto.Token
import ks.hs.dgsw.domain.entity.request.Password
import javax.inject.Inject

class PasswordDataSource @Inject constructor(
    override val remote: PasswordRemote
): BaseDataSource<PasswordRemote>() {
    suspend fun postPasswordRegister(password: Password): String {
        return remote.postPasswordRegister(password)
    }

    suspend fun postPasswordLogin(password: Password): Token {
        return remote.postPasswordLogin(password).toEntity()
    }
}