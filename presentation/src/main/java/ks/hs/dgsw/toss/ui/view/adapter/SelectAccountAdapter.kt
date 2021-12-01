package ks.hs.dgsw.toss.ui.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.toss.databinding.ItemSelectAccountBinding

class SelectAccountAdapter: RecyclerView.Adapter<SelectAccountAdapter.ViewHolder>() {

    private val selectedIdList = ArrayList<String>()
    private val list = ArrayList<Account>()

    fun getSelectedList(): ArrayList<String> {
        return selectedIdList
    }

    fun addSelectItem(account: String) {
        selectedIdList.add(account)

        Log.d("", "addSelectItem: $selectedIdList")
    }

    fun deleteSelectItem(account: String) {
        if (selectedIdList.contains(account)) {
            selectedIdList.remove(account)
            Log.d("", "addSelectItem: $selectedIdList")
        }
    }

    fun submitList(newList: List<Account>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemSelectAccountBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) {
            binding.account = account
            binding.tvBankNameItemSelectAccount.text =
                when(account.account.substring(0, 3)) {
                    "001" -> "카카오뱅크"
                    "002" -> "토스"
                    "003" -> "신한은행"
                    else -> ""
                }
            binding.chkItemSelectAccount.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) addSelectItem(account.account) else deleteSelectItem(account.account)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSelectAccountBinding.inflate(inflater)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            MATCH_PARENT, WRAP_CONTENT
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
