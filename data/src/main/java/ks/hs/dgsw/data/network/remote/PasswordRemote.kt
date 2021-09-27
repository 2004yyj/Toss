package ks.hs.dgsw.data.network.remote

import ks.hs.dgsw.data.base.BaseRemote
import ks.hs.dgsw.data.network.service.PasswordService
import ks.hs.dgsw.domain.entity.request.Password
import javax.inject.Inject

class PasswordRemote @Inject constructor(
    override val service: PasswordService
): BaseRemote<PasswordService>() {
    suspend fun postPasswordRegister(password: Password): String {
        return getMessage(service.postPasswordRegister(password))
    }

    suspend fun postPasswordLogin(password: Password): String {
        return getTokenResponse(service.postPasswordLogin(password))
    }
}