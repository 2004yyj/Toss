package ks.hs.dgsw.toss.ui.viewmodel.activity

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.dto.TransferHistory
import ks.hs.dgsw.domain.entity.dto.TransferHistoryComparatorDescending
import ks.hs.dgsw.domain.usecase.account.GetAccountByAccountNumberUseCase
import javax.inject.Inject

@HiltViewModel
class AccountDetailViewModel @Inject constructor(
    private val getAccountByAccountNumberUseCase: GetAccountByAccountNumberUseCase
): ViewModel() {

    val accountBankName = ObservableField<String>()
    val accountId = ObservableField<String>()
    val accountMoney = ObservableField<String>()
    private val _transferHistory = MutableLiveData<ArrayList<TransferHistory>>()
    val transferHistory: LiveData<ArrayList<TransferHistory>> = _transferHistory

    fun getAccountInfo(account: String) {
        viewModelScope.launch {
            val params = GetAccountByAccountNumberUseCase.Params(account)
            val accountEntity = getAccountByAccountNumberUseCase.buildParamsUseCase(params)

            val bankName = when(accountEntity.account.substring(0, 3)) {
                "001" -> {  "카카오뱅크" }
                "002" -> { "토스" }
                "003" -> { "신한은행" }
                else -> { "" }
            }

            accountBankName.set("$bankName 계좌")
            accountId.set("$bankName ${accountEntity.account}")
            accountMoney.set(String.format("%,d원", accountEntity.money))
        }
    }

}