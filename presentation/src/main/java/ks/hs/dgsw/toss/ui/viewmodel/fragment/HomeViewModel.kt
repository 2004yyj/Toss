package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ks.hs.dgsw.domain.entity.Account
import ks.hs.dgsw.toss.ui.view.util.Event

class HomeViewModel(): ViewModel() {
    val accountList = ObservableArrayList<Account>()

    private val _openRemitPage = MutableLiveData<Event<String>>()
    val openRemitPage = _openRemitPage

    fun getUserInfo() {

    }

    fun getAccounts() {
        accountList.clear()
        accountList.addAll(arrayListOf(Account("대구은행", "20513158980", "양윤재", 5000)))
        accountList.addAll(arrayListOf(Account("우리은행", "50113158980", "양윤재", 5000)))
        accountList.addAll(arrayListOf(Account("토스", "60413158980", "양윤재", 5000)))
    }

    fun remitPage() {
        _openRemitPage.value = Event("openRemitPage")
    }

}