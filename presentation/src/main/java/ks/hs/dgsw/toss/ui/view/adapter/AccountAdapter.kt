package ks.hs.dgsw.toss.ui.view.adapter

import android.content.ContextWrapper
import android.content.Intent
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
import ks.hs.dgsw.toss.databinding.ItemAccountEmptyFirstBinding
import ks.hs.dgsw.toss.databinding.ItemAccountEmptySecondBinding
import ks.hs.dgsw.toss.ui.view.activity.AddAccountActivity
import ks.hs.dgsw.toss.ui.view.activity.ConnectAccountActivity
import ks.hs.dgsw.toss.ui.view.activity.MainActivity
import ks.hs.dgsw.toss.ui.view.activity.RemitActivity

class AccountAdapter: ListAdapter<Account, RecyclerView.ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return if (super.getItemCount() == 0) {
            if (position == 0) {
                TYPE_EMPTY_FIRST
            } else {
                TYPE_EMPTY_SECOND
            }
        } else {
            TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return if (super.getItemCount() == 0) 2 else super.getItemCount()
    }

    inner class AccountViewHolder(private val binding: ItemAccountBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) = with(binding) {
            binding.account = account
            btnRemitAccount.setOnClickListener {
                val intent = Intent(it.context, RemitActivity::class.java)
                it.context.startActivity(intent)
                ((it.context as ContextWrapper).baseContext as AppCompatActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }

    inner class AccountEmptyFirstViewHolder(private val binding: ItemAccountEmptyFirstBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            btnAddAccountLast.setOnClickListener {
                val intent = Intent(it.context, AddAccountActivity::class.java)
                it.context.startActivity(intent)
                ((it.context as ContextWrapper).baseContext as AppCompatActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }

    inner class AccountEmptySecondViewHolder(private val binding: ItemAccountEmptySecondBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            btnConnectAccountLast.setOnClickListener {
                val intent = Intent(it.context, ConnectAccountActivity::class.java)
                it.context.startActivity(intent)
                ((it.context as ContextWrapper).baseContext as AppCompatActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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
            TYPE_EMPTY_FIRST -> {
                val binding = ItemAccountEmptyFirstBinding.inflate(LayoutInflater.from(parent.context))
                binding.root.layoutParams = RecyclerView.LayoutParams(
                    MATCH_PARENT,
                    WRAP_CONTENT
                )
                AccountEmptyFirstViewHolder(binding)
            }
            TYPE_EMPTY_SECOND -> {
                val binding = ItemAccountEmptySecondBinding.inflate(LayoutInflater.from(parent.context))
                binding.root.layoutParams = RecyclerView.LayoutParams(
                    MATCH_PARENT,
                    WRAP_CONTENT
                )
                AccountEmptySecondViewHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is AccountEmptyFirstViewHolder -> {
                holder.bind()
            }
            is AccountEmptySecondViewHolder -> {
                holder.bind()
            }
            is AccountViewHolder -> {
                holder.bind(getItem(position))
            }
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
        const val TYPE_EMPTY_FIRST = 1
        const val TYPE_EMPTY_SECOND = 2
    }
}