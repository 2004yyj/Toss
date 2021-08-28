package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ks.hs.dgsw.domain.entity.Account

class HomeViewModel(): ViewModel() {
    val accountList = ObservableArrayList<Account>()

    fun getAccounts() {
        accountList.clear()
        accountList.addAll(arrayListOf(Account("대구은행", "20513158980", "양윤재", 5000)))
    }
}