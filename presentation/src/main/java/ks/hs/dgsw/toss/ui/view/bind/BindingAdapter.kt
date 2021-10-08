package ks.hs.dgsw.toss.ui.view.bind

import `in`.aabhasjindal.otptextview.OtpTextView
import android.app.Activity
import android.view.View
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.Bank
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

@BindingAdapter("setError")
fun TextInputLayout.setError(errorText: String) {
    this.error = errorText
}

@BindingAdapter("setVisible") // visible 설정 시 visible boolean 값이 false여도 visible하도록 설정
fun View.setVisible(isVisibility: Boolean) {
    if (isVisibility) {
        visibility = VISIBLE
    }
}

@BindingAdapter("value")
fun OtpTextView.setViewBinding(value: String?) {
    val old = this.otp
    if (old != value && !value.isNullOrBlank()) {
        this.setOTP(value)
    }
}

@BindingAdapter("valueAttrChanged")
fun OtpTextView.onValueAttrChanged(listener: InverseBindingListener?) {
    this.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
        listener?.onChange()
    }
}

@InverseBindingAdapter(attribute = "value", event = "valueAttrChanged")
fun bindingValue(otpTextView: OtpTextView): String {
    return otpTextView.otp?:""
}