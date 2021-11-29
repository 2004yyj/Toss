package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SetAccountViewModel(): ViewModel() {
    val bankName = MutableLiveData<String>()
    val accountNumber = MutableLiveData<String>()
}