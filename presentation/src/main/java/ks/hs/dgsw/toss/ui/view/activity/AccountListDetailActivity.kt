package ks.hs.dgsw.toss.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.domain.entity.dto.TransferHistory
import ks.hs.dgsw.domain.entity.dto.TransferHistoryComparatorDescending
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.ActivityAccountBinding
import ks.hs.dgsw.toss.ui.view.adapter.AccountAdapter
import ks.hs.dgsw.toss.ui.view.adapter.AccountAdapter.Companion.REMIT_BUTTON_VISIBLE
import ks.hs.dgsw.toss.ui.viewmodel.activity.AccountListDetailViewModel

@AndroidEntryPoint
class AccountListDetailActivity : AppCompatActivity() {

    private lateinit var accountAdapter: AccountAdapter
    private lateinit var binding: ActivityAccountBinding
    private val viewModel: AccountListDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        create()

        init()
        observe()
        listener()
    }

    private fun create() {
        val inflater = LayoutInflater.from(this)
        binding = ActivityAccountBinding.inflate(inflater)
        setContentView(binding.root)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun init() = with(binding) {
        accountAdapter = AccountAdapter(REMIT_BUTTON_VISIBLE) {
            val intent = Intent(this@AccountListDetailActivity, AccountDetailActivity::class.java)
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
        rvAccountList.adapter = accountAdapter

        viewModel.getAccounts()
    }

    private fun observe() = with(viewModel) {
        accountList.observe(this@AccountListDetailActivity) {
            accountAdapter.submitList(it)
        }
    }

    private fun listener() = with(binding) {
        toolbar.setNavigationOnClickListener {
            finish()
            overridePendingTransition(R.anim.pop_slide_in_left, R.anim.pop_slide_out_right)
        }

        btnAddAccount.setOnClickListener {
            val intent = Intent(this@AccountListDetailActivity, AddAccountActivity::class.java)
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }

        btnConnectAccount.setOnClickListener {
            val intent = Intent(this@AccountListDetailActivity, AddOtherBankAccountActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finishActivity()
    }

    private fun finishActivity() {
        finish()
        overridePendingTransition(
            R.anim.pop_slide_in_left,
            R.anim.pop_slide_out_right
        )
    }


}