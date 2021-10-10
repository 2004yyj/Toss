package ks.hs.dgsw.toss.ui.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.databinding.ActivityMainBinding

/**
 *
 * 메인 화면입니다
 * 홈, 내 소비, 상품, 전체 프래그먼트를
 * BottomNavigationView로 이동할 수 있습니다.
 *
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        navController = (supportFragmentManager
            .findFragmentById(binding.navHostFragmentMain.id) as NavHostFragment)
            .navController
        binding.bnvMain.setupWithNavController(navController)
    }
}