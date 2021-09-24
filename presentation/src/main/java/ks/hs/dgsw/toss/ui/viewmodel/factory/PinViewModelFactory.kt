package ks.hs.dgsw.toss.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ks.hs.dgsw.domain.usecase.password.PostPasswordLoginUseCase
import ks.hs.dgsw.domain.usecase.password.PostPasswordRegisterUseCase
import ks.hs.dgsw.toss.ui.viewmodel.fragment.PinViewModel

class PinViewModelFactory(
    private val postPasswordRegisterUseCase: PostPasswordRegisterUseCase,
    private val postPasswordLoginUseCase: PostPasswordLoginUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PinViewModel::class.java)) {
            PinViewModel(postPasswordRegisterUseCase, postPasswordLoginUseCase) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}