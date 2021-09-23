package ks.hs.dgsw.toss.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ks.hs.dgsw.domain.usecase.user.PostLoginUseCase
import ks.hs.dgsw.toss.ui.viewmodel.fragment.LoginViewModel

class LoginViewModelFactory(
    private val postLoginUseCase: PostLoginUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(postLoginUseCase) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}