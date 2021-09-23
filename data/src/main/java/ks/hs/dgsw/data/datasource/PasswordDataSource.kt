package ks.hs.dgsw.data.datasource

import ks.hs.dgsw.data.base.BaseDataSource
import ks.hs.dgsw.data.network.remote.PasswordRemote
import javax.inject.Inject

class PasswordDataSource @Inject constructor(
    override val remote: PasswordRemote
): BaseDataSource<PasswordRemote>() {
    suspend fun postPasswordRegister(pw: String): String {
        return remote.postPasswordRegister(pw)
    }

    suspend fun postPasswordLogin(pw: String): String {
        return remote.postPasswordLogin(pw)
    }
}