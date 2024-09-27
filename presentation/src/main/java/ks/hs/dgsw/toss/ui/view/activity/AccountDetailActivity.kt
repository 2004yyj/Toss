package ks.hs.dgsw.toss.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.domain.entity.dto.TransferHistory
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.ActivityAccountDetailBinding
import ks.hs.dgsw.toss.ui.view.adapter.TransferHistoryAdapter
import ks.hs.dgsw.toss.ui.viewmodel.activity.AccountDetailViewModel

@AndroidEntryPoint
class AccountDetailActivity : AppCompatActivity() {

    private val viewModel: AccountDetailViewModel by viewModels()
    private lateinit var binding: ActivityAccountDetailBinding
    private lateinit var adapter: TransferHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountDetailBinding.inflate(layoutInflater)
        binding.vm = viewModel
        setContentView(binding.root)

        val accountId = intent.getStringExtra("accountId") ?: ""
        val transferHistoryList: List<TransferHistory> = intent.getParcelableArrayListExtra("transferHistoryList") ?: ArrayList()

        viewModel.getAccountInfo(accountId)

        binding.toolbarAccountDetail.setNavigationOnClickListener {
            finish()
        }

        binding.btnRemitAccountDetail.setOnClickListener {
            val intent = Intent(this, RemitActivity::class.java)
            intent.putExtra("senderAccountNumber", accountId)
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }

        adapter = TransferHistoryAdapter(accountId)
        binding.rvHistoryAccountDetail.adapter = adapter
        adapter.submitList(transferHistoryList)
    }
}