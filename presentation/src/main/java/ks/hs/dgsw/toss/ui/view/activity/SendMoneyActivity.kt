package ks.hs.dgsw.toss.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.ActivitySendMoneyBinding

@AndroidEntryPoint
class SendMoneyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySendMoneyBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMoneyBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        navController = (supportFragmentManager
            .findFragmentById(binding.navHostFragmentSendMoney.id) as NavHostFragment)
            .navController

        val accountNumber = intent.getStringExtra("senderAccountNumber")

        if (accountNumber == null) {
            val curGraph = navController.graph
            curGraph.startDestination = R.id.setAccountFragment
            navController.graph = curGraph
        }
    }
}