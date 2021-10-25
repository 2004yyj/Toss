package ks.hs.dgsw.toss.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentHomeBinding
import ks.hs.dgsw.toss.ui.view.activity.AddAccountActivity
import ks.hs.dgsw.toss.ui.view.activity.ConnectAccountActivity
import ks.hs.dgsw.toss.ui.view.activity.RemitActivity
import ks.hs.dgsw.toss.ui.view.adapter.AccountAdapter
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

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var accountAdapter: AccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroy() {
        binding.lifecycleOwner = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observe()
        listener()
    }

    override fun onResume() {
        super.onResume()

        init()
    }

    private fun listener() = with(binding) {
        fabAddAccountList.setOnClickListener {
            val intent = Intent(requireActivity(), AddAccountActivity::class.java)
            requireActivity().startActivity(intent)
        }

        fabConnectAccountList.setOnClickListener {
            val intent = Intent(requireActivity(), ConnectAccountActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }

    private fun observe() {
        viewModel.openRemitPage.observe(viewLifecycleOwner, EventObserver {
            val intent = Intent(context, RemitActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        })
        viewModel.accountList.observe(viewLifecycleOwner) {
            accountAdapter.submitList(
                if (it.size <= 2) {
                    it
                } else {
                    it.subList(0, 2)
                })
        }
        viewModel.isFailure.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView() = with(binding) {
        accountAdapter = AccountAdapter()
        rvSummarizedAccountList.adapter = accountAdapter
    }

    private fun init() {
        viewModel.getUserInfo()
    }
}