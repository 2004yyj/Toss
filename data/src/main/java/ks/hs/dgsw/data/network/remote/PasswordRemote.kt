package ks.hs.dgsw.data.network.remote

import ks.hs.dgsw.data.base.BaseRemote
import ks.hs.dgsw.data.network.service.PasswordService
import javax.inject.Inject

class PasswordRemote @Inject constructor(
    override val service: PasswordService
): BaseRemote<PasswordService>() {
    suspend fun postPasswordRegister(pw: String): String {
        return getMessage(service.postPasswordRegister(pw))
    }

    suspend fun postPasswordLogin(pw: String): String {
        return getTokenResponse(service.postPasswordLogin(pw))
    }
}