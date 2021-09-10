package ks.hs.dgsw.data.datasource

import ks.hs.dgsw.data.base.BaseDataSource
import ks.hs.dgsw.data.mapper.toEntity
import ks.hs.dgsw.data.network.remote.UserRemote
import ks.hs.dgsw.domain.entity.dto.User
import ks.hs.dgsw.domain.entity.request.Login
import ks.hs.dgsw.domain.entity.request.Register
import javax.inject.Inject

class UserDataSource @Inject constructor(
    override val remote: UserRemote
): BaseDataSource<UserRemote>() {
    suspend fun postRegister(register: Register): String {
        return remote.postRegister(register)
    }

    suspend fun postLogin(login: Login): String {
        return remote.postLogin(login)
    }

    suspend fun getMyInfo(): User {
        return remote.getMyInfo().toEntity()
    }
}