package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddOtherBankAccountFirstViewModel(): ViewModel() {
    val name = MutableLiveData<String>()
    val securityNumberFirst = MutableLiveData<String>()
    val securityNumberSecond = MutableLiveData<String>()
}