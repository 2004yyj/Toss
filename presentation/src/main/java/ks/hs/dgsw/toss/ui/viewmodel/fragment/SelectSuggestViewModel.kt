package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ks.hs.dgsw.domain.entity.Account
import ks.hs.dgsw.toss.ui.view.util.Event

class SelectSuggestViewModel(): ViewModel() {
    val accountList = ObservableArrayList<Account>()

    private val _openRemitPage = MutableLiveData<Event<String>>()
    val openRemitPage = _openRemitPage

    fun getUserInfo() {

    }

    fun getAccounts() {
        accountList.clear()
        for(i in 0..10) {
            accountList.addAll(arrayListOf(Account("대구은행", "20513158980", "엄준식", 5000)))
        }
    }

    fun remitPage() {
        _openRemitPage.value = Event("openRemitPage")
    }

}