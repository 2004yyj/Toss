package ks.hs.dgsw.toss.ui.view.util

import android.util.Log
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.loginToken
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.registerToken
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = if (loginToken.isNullOrEmpty()) {
            chain.request()
                .newBuilder()
                .addHeader("authorization", registerToken ?: "")
                .build()
        } else {
            chain.request()
                .newBuilder()
                .addHeader("authorization", loginToken ?: "")
                .build()
        }
        return chain.proceed(request)
    }
}