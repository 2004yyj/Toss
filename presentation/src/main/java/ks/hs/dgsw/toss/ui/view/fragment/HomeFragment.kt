package ks.hs.dgsw.toss.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentHomeBinding
import ks.hs.dgsw.toss.ui.view.activity.RemitActivity
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.viewmodel.factory.HomeViewModelFactory
import ks.hs.dgsw.toss.ui.viewmodel.fragment.HomeViewModel

/**
 *
 * 홈 프래그먼트에서는 나의 프로필, 계좌 목록,
 * 알림을 확인할 수 있고
 * 송금을 진행할 수 있습니다.
 *
 */

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val factory by lazy { HomeViewModelFactory() }
    private val viewModel by viewModels<HomeViewModel> { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewModel.getAccounts()
        viewModel.openRemitPage.observe(viewLifecycleOwner, EventObserver {
            val intent = Intent(context, RemitActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        })
    }
}