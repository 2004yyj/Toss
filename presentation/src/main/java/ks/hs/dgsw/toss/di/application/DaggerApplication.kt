package ks.hs.dgsw.toss.di.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper

@HiltAndroidApp
class DaggerApplication: Application() {
    override fun onCreate() {
        PreferenceHelper.init(applicationContext)
        super.onCreate()
    }
}