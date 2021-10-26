package ks.hs.dgsw.toss.ui.viewmodel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.usecase.account.GetAccountsByTokenUseCase
import ks.hs.dgsw.toss.ui.view.util.Event
import javax.inject.Inject

@HiltViewModel
class AccountListDetailViewModel @Inject constructor(
    private val getAccountsByTokenUseCase: GetAccountsByTokenUseCase
): ViewModel() {
    val accountList = MutableLiveData<ArrayList<Account>>()
    val totalMoney = MutableLiveData<Int>(0)

    private val _isFailure = MutableLiveData<Event<String>>()
    val isFailure: LiveData<Event<String>> = _isFailure

    fun getAccounts() {
        viewModelScope.launch {
            val baseAccount = getAccountsByTokenUseCase.buildUseCase()
            try {
                var totalMoneyBuilder = 0
                with(baseAccount) {
                    accountList.value = ArrayList(accounts)
                    accountList.value?.forEach { totalMoneyBuilder += it.money }
                    totalMoney.value = totalMoneyBuilder
                }
            } catch (e: Throwable) {
                _isFailure.value = Event(e.message ?: "")
            }
        }
    }

}
