package ks.hs.dgsw.toss.ui.view.bind

import android.app.Activity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ks.hs.dgsw.domain.entity.Account
import ks.hs.dgsw.domain.entity.Bank
import ks.hs.dgsw.toss.ui.view.adapter.AccountAdapter
import ks.hs.dgsw.toss.ui.view.adapter.BankAdapter
import ks.hs.dgsw.toss.ui.view.decoration.GridLayoutSpacingDecoration
import ks.hs.dgsw.toss.ui.view.util.NonScrollLinearLayoutManager

@BindingAdapter("type", "submitList") // setAccountList
fun RecyclerView.submitList(type: Int, list: List<Account>) {
    if (adapter == null) {
        adapter = AccountAdapter(type)
        layoutManager = NonScrollLinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    }
    (adapter as AccountAdapter).submitList(list)
}

@BindingAdapter("submitList") // setBankList
fun RecyclerView.submitList(list: List<Bank>) {
    if (adapter == null) {
        adapter = BankAdapter()
        addItemDecoration(GridLayoutSpacingDecoration((context as Activity), 3))
    }
    (adapter as BankAdapter).submitList(list)
}