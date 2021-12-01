package ks.hs.dgsw.toss.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R

@AndroidEntryPoint
class AddOtherBankAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_other_bank_account)
    }
}