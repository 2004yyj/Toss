package ks.hs.dgsw.toss.ui.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.ItemAccountBinding
import ks.hs.dgsw.toss.databinding.ItemAccountUserBinding
import ks.hs.dgsw.toss.ui.view.activity.MainActivity
import ks.hs.dgsw.toss.ui.view.activity.RemitActivity

class AccountAdapter(private val viewType: Int): ListAdapter<Account, RecyclerView.ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    inner class AccountViewHolder(private val binding: ItemAccountBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) = with(binding) {
            binding.account = account
            btnRemitAccount.setOnClickListener {
                val intent = Intent(it.context, RemitActivity::class.java)
                it.context.startActivity(intent)
                (it.context as MainActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }

    inner class AccountUserViewHolder(private val binding: ItemAccountUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) {
            binding.account = account
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            MY_ACCOUNT_TYPE -> {
                val binding = ItemAccountBinding.inflate(LayoutInflater.from(parent.context))
                binding.root.layoutParams = RecyclerView.LayoutParams(
                    MATCH_PARENT,
                    WRAP_CONTENT
                )
                return AccountViewHolder(binding)
            }
            OTHER_USER_ACCOUNT_TYPE -> {
                val binding = ItemAccountUserBinding.inflate(LayoutInflater.from(parent.context))
                binding.root.layoutParams = RecyclerView.LayoutParams(
                    MATCH_PARENT,
                    WRAP_CONTENT
                )
                return AccountUserViewHolder(binding)
            }
            else -> throw IndexOutOfBoundsException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (true) {
            holder is AccountViewHolder -> holder.bind(getItem(position))
            holder is AccountUserViewHolder -> holder.bind(getItem(position))
            else -> throw IllegalArgumentException()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Account>() {
            override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
                return oldItem == newItem
            }

        }

        const val MY_ACCOUNT_TYPE = 0
        const val OTHER_USER_ACCOUNT_TYPE = 1
    }
}