package ks.hs.dgsw.toss.ui.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ks.hs.dgsw.toss.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(
            Intent(
            this, AuthActivity::class.java
            )
        )
        finish()
    }
}