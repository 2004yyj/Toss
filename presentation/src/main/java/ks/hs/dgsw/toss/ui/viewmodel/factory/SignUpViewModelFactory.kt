package ks.hs.dgsw.toss.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ks.hs.dgsw.toss.ui.viewmodel.activity.SignUpViewModel

class SignUpViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel() as T
        } else {
            throw IllegalArgumentException()
        }
    }
}