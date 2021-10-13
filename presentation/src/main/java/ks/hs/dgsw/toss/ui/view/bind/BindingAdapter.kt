package ks.hs.dgsw.toss.ui.view.bind

import `in`.aabhasjindal.otptextview.OtpTextView
import android.app.Activity
import android.util.Log
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
    if (old != value && value != null) {
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