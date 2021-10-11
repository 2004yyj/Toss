package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.toss.ui.view.util.Event

class SelectSuggestViewModel(): ViewModel() {
    val accountList = MutableLiveData(ArrayList<Account>())

    private val _openRemitPage = MutableLiveData<Event<String>>()
    val openRemitPage = _openRemitPage

    fun getAccounts() {
    }

    fun remitPage() {
        _openRemitPage.value = Event("openRemitPage")
    }

}