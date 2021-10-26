package ks.hs.dgsw.data.network.remote

import ks.hs.dgsw.data.base.BaseRemote
import ks.hs.dgsw.data.entity.LoginTokenData
import ks.hs.dgsw.data.entity.RegisterTokenData
import ks.hs.dgsw.data.entity.UserData
import ks.hs.dgsw.data.network.service.UserService
import ks.hs.dgsw.domain.entity.request.Login
import ks.hs.dgsw.domain.entity.request.Register
import javax.inject.Inject

class UserRemote @Inject constructor(
    override val service: UserService
): BaseRemote<UserService>() {
    suspend fun postRegister(register: Register): RegisterTokenData {
        return getResponse(service.postRegister(register))
    }

    suspend fun postLogin(login: Login): LoginTokenData {
        return getResponse(service.postLogin(login))
    }

    suspend fun getMyInfo(): UserData {
        return getResponse(service.getMyInfo())
    }

    suspend fun getInfoByBirthAndName(
        birth: String,
        name: String
    ): UserData {
        return getResponse(service.getInfoByBirthAndName(birth, name))
    }

    suspend fun getCheckId(id: String): Boolean {
        return getResponse(service.getCheckId(id))
    }

    suspend fun getCheckNick(nick: String): Boolean {
        return getResponse(service.getCheckNick(nick))
    }
}