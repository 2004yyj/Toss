package ks.hs.dgsw.toss.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.toss.databinding.ItemAccountUserBinding

class UserAccountAdapter(): ListAdapter<Account, UserAccountAdapter.AccountUserViewHolder>(diffUtil) {

    inner class AccountUserViewHolder(private val binding: ItemAccountUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) {
            binding.account = account
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountUserViewHolder {
        val binding = ItemAccountUserBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.layoutParams = RecyclerView.LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
        return AccountUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountUserViewHolder, position: Int) {
        holder.bind(getItem(position))
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
    }
}