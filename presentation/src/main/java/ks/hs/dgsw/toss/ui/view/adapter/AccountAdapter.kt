package ks.hs.dgsw.toss.ui.view.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.ItemAccountBinding
import ks.hs.dgsw.toss.databinding.ItemAccountLastBinding
import ks.hs.dgsw.toss.ui.view.activity.AccountActivity
import ks.hs.dgsw.toss.ui.view.activity.MainActivity
import ks.hs.dgsw.toss.ui.view.activity.RemitActivity

class AccountAdapter: ListAdapter<Account, RecyclerView.ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return if (itemCount == 1 || position == itemCount) {
            TYPE_LAST
        } else {
            TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
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

    inner class AccountLastViewHolder(private val binding: ItemAccountLastBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            btnAddAccountLast.setOnClickListener {
                val intent = Intent(it.context, AccountActivity::class.java)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_ITEM -> {
                val binding = ItemAccountBinding.inflate(LayoutInflater.from(parent.context))
                binding.root.layoutParams = RecyclerView.LayoutParams(
                    MATCH_PARENT,
                    WRAP_CONTENT
                )
                AccountViewHolder(binding)
            }
            TYPE_LAST -> {
                val binding = ItemAccountLastBinding.inflate(LayoutInflater.from(parent.context))
                binding.root.layoutParams = RecyclerView.LayoutParams(
                    MATCH_PARENT,
                    WRAP_CONTENT
                )
                AccountLastViewHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AccountLastViewHolder) {
            holder.bind()
        } else if (holder is AccountViewHolder) {
            holder.bind(getItem(position))
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

        const val TYPE_ITEM = 0
        const val TYPE_LAST = 1
    }
}