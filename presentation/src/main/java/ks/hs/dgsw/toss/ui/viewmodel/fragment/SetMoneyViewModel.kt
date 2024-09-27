package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.usecase.account.GetAccountByAccountNumberUseCase
import ks.hs.dgsw.domain.usecase.account.GetOtherAccountForBankUseCase
import javax.inject.Inject

@HiltViewModel
class SetMoneyViewModel @Inject constructor(
    private val getOtherAccountForBankUseCase: GetOtherAccountForBankUseCase
): ViewModel() {
    private val _isGetReceiverSuccess = MutableLiveData<Account>()
    val isGetReceiverSuccess: LiveData<Account> = _isGetReceiverSuccess

    private val _isGetSenderSuccess = MutableLiveData<Account>()
    val isGetSenderSuccess: LiveData<Account> = _isGetSenderSuccess

    var bankCode = 0
    var senderAccountId = ""
    var receiverAccountId = ""
    val money = MutableLiveData<Int>()

    fun setMoney(value: String) {
        var stringMoney = (money.value ?: 0).toString()
        stringMoney = (stringMoney + value)
        if (stringMoney.toInt() <= 10000000) {
            money.value = stringMoney.toInt()
        }
    }

    fun backspaceMoney() {
        var stringMoney = (money.value?:0).toString()
        stringMoney = if (stringMoney.length <= 1) {
            "0"
        } else {
            stringMoney.substring(0 until stringMoney.lastIndex)
        }
        money.value = stringMoney.toInt()
    }

    fun clearMoney() {
        money.value = 0
    }

    fun getAccount(bankCode: Int, accountNumber: String) {
        viewModelScope.launch {
            val params = GetOtherAccountForBankUseCase.Params(bankCode, accountNumber)
            _isGetReceiverSuccess.value = getOtherAccountForBankUseCase.buildParamsUseCase(params)
        }
    }

    fun getMyAccount(bankCode: Int, accountNumber: String) {
        viewModelScope.launch {
            val params = GetOtherAccountForBankUseCase.Params(bankCode, accountNumber)
            _isGetSenderSuccess.value = getOtherAccountForBankUseCase.buildParamsUseCase(params)
        }
    }
}