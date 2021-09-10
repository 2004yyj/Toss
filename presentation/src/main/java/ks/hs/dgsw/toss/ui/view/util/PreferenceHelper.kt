package ks.hs.dgsw.toss.ui.view.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object PreferenceHelper {
    private lateinit var sharedPreferences: SharedPreferences
    fun init(context: Context) { sharedPreferences = context.getSharedPreferences("AuthInfo", MODE_PRIVATE) }

    var token: String
        get() = sharedPreferences.getString("token", "")?:""
        set(value) {
            val edit = sharedPreferences.edit()
            edit.putString("token", value)
            edit.apply()
        }
}