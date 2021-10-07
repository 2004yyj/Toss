package ks.hs.dgsw.toss.ui.view.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object PreferenceHelper {
    private lateinit var sharedPreferences: SharedPreferences
    fun init(context: Context) { sharedPreferences = context.getSharedPreferences("AuthInfo", MODE_PRIVATE) }

    var loginToken: String?
        get() = sharedPreferences.getString("loginToken", "")
        set(value) {
            val edit = sharedPreferences.edit()
            edit.putString("loginToken", value)
            edit.apply()
        }

    var registerToken: String?
        get() = sharedPreferences.getString("registerToken", "")
        set(value) {
            val edit = sharedPreferences.edit()
            edit.putString("registerToken", value)
            edit.apply()
        }

    var passwordLoginId: String?
        get() = sharedPreferences.getString("passwordLoginId", "")
        set(value) {
            val edit = sharedPreferences.edit()
            edit.putString("passwordLoginId", value)
            edit.apply()
        }
}