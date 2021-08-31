package ks.hs.dgsw.toss.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ks.hs.dgsw.toss.ui.viewmodel.fragment.HomeViewModel
import ks.hs.dgsw.toss.ui.viewmodel.fragment.SelectSuggestViewModel

class SelectSuggestViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SelectSuggestViewModel::class.java)) {
            SelectSuggestViewModel() as T
        } else {
            throw IllegalArgumentException()
        }
    }
}