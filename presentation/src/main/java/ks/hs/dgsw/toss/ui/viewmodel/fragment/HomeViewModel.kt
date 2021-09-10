package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.toss.ui.view.util.Event

class HomeViewModel(): ViewModel() {
    val accountList = ObservableArrayList<Account>()

    private val _openRemitPage = MutableLiveData<Event<String>>()
    val openRemitPage = _openRemitPage

    fun getUserInfo() {

    }

    fun getAccounts() {

    }

    fun remitPage() {
        _openRemitPage.value = Event("openRemitPage")
    }

}