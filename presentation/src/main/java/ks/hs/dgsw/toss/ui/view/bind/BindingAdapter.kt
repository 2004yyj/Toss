package ks.hs.dgsw.toss.ui.view.bind

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ks.hs.dgsw.domain.entity.Account
import ks.hs.dgsw.toss.ui.view.adapter.AccountAdapter
import ks.hs.dgsw.toss.ui.view.util.NonScrollLinearLayoutManager

@BindingAdapter("submitList") // setAccountList
fun RecyclerView.submitList(list: List<Account>) {
    if (adapter == null) {
        adapter = AccountAdapter()
        layoutManager = NonScrollLinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    }
    (adapter as AccountAdapter).submitList(list)
}