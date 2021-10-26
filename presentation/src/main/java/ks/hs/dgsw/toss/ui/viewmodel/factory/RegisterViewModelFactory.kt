package ks.hs.dgsw.toss.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ks.hs.dgsw.domain.usecase.user.GetCheckIdUseCase
import ks.hs.dgsw.domain.usecase.user.GetCheckNickUseCase
import ks.hs.dgsw.domain.usecase.user.PostRegisterUseCase
import ks.hs.dgsw.toss.ui.viewmodel.activity.RegisterViewModel

class RegisterViewModelFactory(
    private val postRegisterUseCase: PostRegisterUseCase,
    private val getCheckIdUseCase: GetCheckIdUseCase,
    private val getCheckNickUseCase: GetCheckNickUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(postRegisterUseCase, getCheckIdUseCase, getCheckNickUseCase) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}