package ks.hs.dgsw.toss.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ks.hs.dgsw.toss.ui.viewmodel.fragment.SelectAccountViewModel

class SelectAccountViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SelectAccountViewModel::class.java)) {
            SelectAccountViewModel() as T
        } else {
            throw IllegalArgumentException()
        }
    }
}