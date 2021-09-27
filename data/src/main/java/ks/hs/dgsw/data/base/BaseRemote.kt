package ks.hs.dgsw.data.base

import android.util.Log
import com.google.gson.Gson
import ks.hs.dgsw.domain.entity.response.Response
import ks.hs.dgsw.domain.entity.response.TokenResponse

abstract class BaseRemote<SV> {
    protected abstract val service: SV

    fun <T> getResponse(response: retrofit2.Response<Response<T>>): T {
        checkError(response)
        return response.body()!!.data
    }

    fun <T> getMessage(response: retrofit2.Response<Response<T>>): String {
        checkError(response)
        return response.body()!!.message
    }

    fun getTokenResponse(response: retrofit2.Response<TokenResponse>): String {
        checkError(response)
        return response.body()!!.token!!
    }

    private fun <T> checkError(response: retrofit2.Response<T>) {
        if (!response.isSuccessful) {
            val gson = Gson()
            Log.d("BaseRemote", "checkError: ${response.code()}")
            val errorBody = gson.fromJson(response.errorBody()!!.charStream(), Response::class.java)
            throw Throwable(errorBody.message)
        }
    }
}