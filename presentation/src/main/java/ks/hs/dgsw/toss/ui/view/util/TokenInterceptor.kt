package ks.hs.dgsw.toss.ui.view.util

import android.util.Log
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.token
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("authorization", token).build()
        Log.d("TokenInterceptor", "intercept: $token")
        return chain.proceed(request)
    }
}