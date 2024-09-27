package ks.hs.dgsw.toss.ui.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ks.hs.dgsw.domain.entity.dto.Bank
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.ItemBankBinding

class BankAdapter(
    private val onClickBankItem: (Bank) -> Unit
): ListAdapter<Bank, BankAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemBankBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bank: Bank) {
            binding.bank = bank
            binding.btnSelectBankItemBank.setOnClickListener {
                onClickBankItem.invoke(bank)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBankBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Bank>() {
            override fun areItemsTheSame(oldItem: Bank, newItem: Bank): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Bank, newItem: Bank): Boolean {
                return oldItem == newItem
            }

        }
    }
}