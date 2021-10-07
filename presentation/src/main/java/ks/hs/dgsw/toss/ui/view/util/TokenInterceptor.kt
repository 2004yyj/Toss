package ks.hs.dgsw.toss.ui.view.util

import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.loginToken
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.registerToken
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request
        if (!registerToken.isNullOrEmpty() || loginToken.isNullOrEmpty()) {
            request = chain.request()
                .newBuilder()
                .addHeader("authorization", registerToken ?: "")
                .build()
            registerToken = null
        } else {
            request = chain.request()
                .newBuilder()
                .addHeader("authorization", loginToken ?: "")
                .build()
        }
        return chain.proceed(request)
    }
}