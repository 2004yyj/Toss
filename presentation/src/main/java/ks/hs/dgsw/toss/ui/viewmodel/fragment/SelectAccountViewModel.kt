package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ks.hs.dgsw.domain.entity.dto.Bank
import ks.hs.dgsw.toss.ui.view.util.Event

class SelectAccountViewModel: ViewModel() {
    val accountNumber = MutableLiveData<String>()
    val bankList = MutableLiveData(ArrayList<Bank>())

    private val _openSetAccountNumberPage = MutableLiveData<Event<String>>()
    val openRemitPage = _openSetAccountNumberPage

    fun getBanks() {
        bankList.value!!.clear()
        bankList.value!!.addAll(arrayListOf(Bank("토스", 2), Bank("신한은행", 3), Bank("카카오뱅크", 1)))
    }

    fun remitPage() {
        _openSetAccountNumberPage.value = Event("openSetAccountNumberPage")
    }

}