package ks.hs.dgsw.toss.ui.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.TransferHistory
import ks.hs.dgsw.domain.entity.dto.TransferHistoryComparatorDescending
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.ItemAccountBinding
import ks.hs.dgsw.toss.ui.view.activity.RemitActivity

class AccountAdapter(
    private val remitButtonVisibility: Int,
    private val onClickItem: (Account) -> Unit
): ListAdapter<Account, AccountAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemAccountBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) = with(binding) {
            binding.account = account
            btnRemitAccount.visibility = remitButtonVisibility
            btnRemitAccount.setOnClickListener {
                with(it.context as AppCompatActivity) {
                    val intent = Intent(this, RemitActivity::class.java)
                    val transferHistoryList = ArrayList<TransferHistory>()
                    if (account.send != null && account.receive != null) {
                        transferHistoryList.addAll(account.send!!)
                        transferHistoryList.addAll(account.receive!!)
                        transferHistoryList.sortWith(TransferHistoryComparatorDescending())
                    }
                    intent.putExtra("transferHistoryList", transferHistoryList)
                    intent.putExtra("senderAccountNumber", account.account)

                    startActivity(intent)
                    overridePendingTransition(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                    )
                }
            }
            btnItemAccount.setOnClickListener {
                onClickItem.invoke(account)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAccountBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.layoutParams = RecyclerView.LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        const val REMIT_BUTTON_VISIBLE = View.VISIBLE
        const val REMIT_BUTTON_INVISIBLE = View.INVISIBLE

        val diffUtil = object : DiffUtil.ItemCallback<Account>() {
            override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
                return oldItem == newItem
            }
        }
    }
}