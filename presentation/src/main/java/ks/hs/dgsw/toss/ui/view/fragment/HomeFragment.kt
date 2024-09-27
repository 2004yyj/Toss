package ks.hs.dgsw.toss.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.domain.entity.dto.TransferHistory
import ks.hs.dgsw.domain.entity.dto.TransferHistoryComparatorDescending
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentHomeBinding
import ks.hs.dgsw.toss.ui.view.activity.*
import ks.hs.dgsw.toss.ui.view.adapter.AccountAdapter
import ks.hs.dgsw.toss.ui.view.adapter.AccountAdapter.Companion.REMIT_BUTTON_VISIBLE
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.loginToken
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
        val baseInflater = LayoutInflater.from(requireActivity())
        binding = FragmentHomeBinding.inflate(baseInflater)
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

        Log.d("LoginToken", "observe: $loginToken")
        initRecyclerView()
        observe()
        listener()
    }

    override fun onResume() {
        super.onResume()

        init()
    }

    private fun listener() = with(binding) {
        btnAddAccount.setOnClickListener {
            with(requireActivity()) {
                val intent = Intent(this, AddAccountActivity::class.java)
                startActivity(intent)
                overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                )
            }
        }

        btnConnectAccount.setOnClickListener {
            with(requireActivity()) {
                val intent = Intent(this, AddOtherBankAccountActivity::class.java)
                startActivity(intent)
                overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                )
            }
        }

        btnViewMyAccountsList.setOnClickListener {
            with(requireActivity()) {
                val intent = Intent(this, AccountListDetailActivity::class.java)
                startActivity(intent)
                overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                )
            }
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

        viewModel.profileImage.observe(viewLifecycleOwner) {
            Log.d("HomeFragment", it)
            binding.ivProfileImageMain.load(it)
        }
    }

    private fun initRecyclerView() = with(binding) {
        accountAdapter = AccountAdapter(REMIT_BUTTON_VISIBLE) {
            val intent = Intent(context, AccountDetailActivity::class.java)
            val transferHistoryList = ArrayList<TransferHistory>()
            if (it.send != null && it.receive != null) {
                transferHistoryList.addAll(it.send!!)
                transferHistoryList.addAll(it.receive!!)
                transferHistoryList.sortWith(TransferHistoryComparatorDescending())
            }
            intent.putExtra("transferHistoryList", transferHistoryList)
            intent.putExtra("accountId", it.account)
            startActivity(intent)
        }
        rvSummarizedAccountList.adapter = accountAdapter
    }

    private fun init() {
        viewModel.getUserInfo()
    }
}