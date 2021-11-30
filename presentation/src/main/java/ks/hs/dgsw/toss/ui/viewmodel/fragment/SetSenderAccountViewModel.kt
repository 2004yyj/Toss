package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.BaseAccount
import ks.hs.dgsw.domain.usecase.account.GetAccountsByTokenUseCase
import javax.inject.Inject

@HiltViewModel
class SetSenderAccountViewModel @Inject constructor(
    private val getAccountsByTokenUseCase: GetAccountsByTokenUseCase
): ViewModel() {
    private val _isSuccess = MutableLiveData<List<Account>>()
    val isSuccess: LiveData<List<Account>> = _isSuccess

    fun getAccounts() {
        viewModelScope.launch {
            _isSuccess.value = getAccountsByTokenUseCase.buildUseCase().accounts
        }
    }

}
