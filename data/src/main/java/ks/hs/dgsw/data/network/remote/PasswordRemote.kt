package ks.hs.dgsw.data.network.remote

import ks.hs.dgsw.data.base.BaseRemote
import ks.hs.dgsw.data.entity.LoginTokenData
import ks.hs.dgsw.data.network.service.PasswordService
import ks.hs.dgsw.domain.entity.request.PasswordLogin
import ks.hs.dgsw.domain.entity.request.PasswordRegister
import javax.inject.Inject

class PasswordRemote @Inject constructor(
    override val service: PasswordService
): BaseRemote<PasswordService>() {
    suspend fun postPasswordRegister(passwordLogin: PasswordRegister): String {
        return getMessage(service.postPasswordRegister(passwordLogin))
    }

    suspend fun postPasswordLogin(passwordLogin: PasswordLogin): LoginTokenData {
        return getResponse(service.postPasswordLogin(passwordLogin))
    }
}