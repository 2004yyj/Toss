package ks.hs.dgsw.data.datasource

import ks.hs.dgsw.data.base.BaseDataSource
import ks.hs.dgsw.data.mapper.toEntity
import ks.hs.dgsw.data.network.remote.PasswordRemote
import ks.hs.dgsw.domain.entity.dto.LoginToken
import ks.hs.dgsw.domain.entity.request.PasswordLogin
import ks.hs.dgsw.domain.entity.request.PasswordRegister
import javax.inject.Inject

class PasswordDataSource @Inject constructor(
    override val remote: PasswordRemote
): BaseDataSource<PasswordRemote>() {
    suspend fun postPasswordRegister(passwordLogin: PasswordRegister): String {
        return remote.postPasswordRegister(passwordLogin)
    }

    suspend fun postPasswordLogin(passwordLogin: PasswordLogin): LoginToken {
        return remote.postPasswordLogin(passwordLogin).toEntity()
    }
}