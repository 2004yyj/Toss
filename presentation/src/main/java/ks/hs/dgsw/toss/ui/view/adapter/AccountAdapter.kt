package ks.hs.dgsw.toss.ui.view.adapter

import android.content.ContextWrapper
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.ItemAccountBinding
import ks.hs.dgsw.toss.ui.view.activity.AddAccountActivity
import ks.hs.dgsw.toss.ui.view.activity.ConnectAccountActivity
import ks.hs.dgsw.toss.ui.view.activity.MainActivity
import ks.hs.dgsw.toss.ui.view.activity.RemitActivity

class AccountAdapter: ListAdapter<Account, AccountAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemAccountBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) = with(binding) {
            binding.account = account
            btnRemitAccount.setOnClickListener {
                val intent = Intent(it.context, RemitActivity::class.java)
                it.context.startActivity(intent)
                ((it.context as ContextWrapper).baseContext as AppCompatActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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