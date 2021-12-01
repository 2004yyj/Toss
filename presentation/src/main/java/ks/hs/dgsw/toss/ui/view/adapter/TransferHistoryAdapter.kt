package ks.hs.dgsw.toss.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import ks.hs.dgsw.domain.entity.dto.TransferHistory
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.ItemTrafficHistoryBinding
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

class TransferHistoryAdapter(
    private val accountId: String
): RecyclerView.Adapter<TransferHistoryAdapter.ViewHolder>() {

    private val list = ArrayList<TransferHistory>()

    fun submitList(newList: List<TransferHistory>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemTrafficHistoryBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(transferHistory: TransferHistory) {

            val instant = Instant.parse(transferHistory.createdAt)
            val date = Date.from(instant)
            val sdf = SimpleDateFormat("yyyy-MM-dd")

            if (transferHistory.toAccount == accountId) {
                binding.tvNameTrafficHistory.text = "입금"
                binding.tvMoneyTrafficHistory.text = String.format("%,d원", transferHistory.money)
                binding.tvDateTrafficHistory.text = sdf.format(date)
                binding.tvMoneyTrafficHistory.setTextColor(
                    itemView.resources.getColor(
                        R.color.main,
                        itemView.resources.newTheme()
                    )
                )
            } else {
                val bankName = when (transferHistory.toAccount.substring(0, 3)) {
                    "001" -> {
                        "카카오뱅크"
                    }
                    "002" -> {
                        "토스"
                    }
                    "003" -> {
                        "신한은행"
                    }
                    else -> {
                        ""
                    }
                }
                binding.tvNameTrafficHistory.text = "송금 ($bankName ${transferHistory.toAccount})"
                binding.tvMoneyTrafficHistory.text = String.format("-%,d원", transferHistory.money)
                binding.tvDateTrafficHistory.text = sdf.format(date)
                binding.tvMoneyTrafficHistory.setTextColor(
                    itemView.resources.getColor(
                        R.color.text_grey,
                        itemView.resources.newTheme()
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTrafficHistoryBinding.inflate(inflater)
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