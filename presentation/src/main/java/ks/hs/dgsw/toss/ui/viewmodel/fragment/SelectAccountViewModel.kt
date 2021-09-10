package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ks.hs.dgsw.domain.entity.dto.Bank
import ks.hs.dgsw.toss.ui.view.util.Event

class SelectAccountViewModel(): ViewModel() {
    val bankList = ObservableArrayList<Bank>()

    private val _openSetAccountNumberPage = MutableLiveData<Event<String>>()
    val openRemitPage = _openSetAccountNumberPage

    fun getBanks() {
        bankList.clear()
        bankList.addAll(arrayListOf(Bank("대구은행"), Bank("우리은행"), Bank("부산은행"), Bank("토스")))
    }

    fun remitPage() {
        _openSetAccountNumberPage.value = Event("openSetAccountNumberPage")
    }

}