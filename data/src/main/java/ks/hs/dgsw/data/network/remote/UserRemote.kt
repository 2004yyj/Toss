package ks.hs.dgsw.data.network.remote

import ks.hs.dgsw.data.base.BaseRemote
import ks.hs.dgsw.data.entity.UserData
import ks.hs.dgsw.data.network.service.UserService
import ks.hs.dgsw.domain.entity.request.Login
import ks.hs.dgsw.domain.entity.request.Register
import javax.inject.Inject

class UserRemote @Inject constructor(
    override val service: UserService
): BaseRemote<UserService>() {
    suspend fun postRegister(register: Register): String {
        return getMessage(service.postRegister(register))
    }

    suspend fun postLogin(login: Login): String {
        return getTokenResponse(service.postLogin(login))
    }

    suspend fun getMyInfo(): UserData {
        return getResponse(service.getMyInfo())
    }
}