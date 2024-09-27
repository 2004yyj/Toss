package ks.hs.dgsw.toss.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ks.hs.dgsw.domain.usecase.account.GetAccountsByTokenUseCase
import ks.hs.dgsw.domain.usecase.user.GetMyInfoUseCase
import ks.hs.dgsw.toss.ui.viewmodel.fragment.HomeViewModel

class HomeViewModelFactory(
    private val getMyInfoUseCase: GetMyInfoUseCase,
    private val getAccountsByTokenUseCase: GetAccountsByTokenUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(getMyInfoUseCase, getAccountsByTokenUseCase) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}