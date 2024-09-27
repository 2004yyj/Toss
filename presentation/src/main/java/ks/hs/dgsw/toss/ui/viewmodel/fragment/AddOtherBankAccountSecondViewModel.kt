package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.request.PostAddOtherAccount
import ks.hs.dgsw.domain.usecase.account.GetOtherAccountsUseCase
import ks.hs.dgsw.domain.usecase.account.PostAddOtherAccountUseCase
import javax.inject.Inject

@HiltViewModel
class AddOtherBankAccountSecondViewModel @Inject constructor(
    private val getOtherAccountsUseCase: GetOtherAccountsUseCase,
    private val postAddOtherAccountUseCase: PostAddOtherAccountUseCase
): ViewModel() {

    private val _isPostAddOtherAccountSuccess = MutableLiveData<String>()
    val isPostAddOtherAccount: LiveData<String> = _isPostAddOtherAccountSuccess

    private val _isGetOtherAccountsSuccess = MutableLiveData<List<Account>>()
    val isGetOtherAccountsSuccess: LiveData<List<Account>> = _isGetOtherAccountsSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure: LiveData<String> = _isFailure

    fun getOtherAccounts(
        birth: String,
        name: String
    ) {
        viewModelScope.launch {
            _isGetOtherAccountsSuccess.value = arrayListOf(
                Account(
                    1,
                    null,
                    null,
                    "001292206167",
                    "자유입출금",
                    "카카오뱅크",
                    "김토스6",
                    1000,
                    null,
                    null
                )
            )
        }
    }

    fun postAddOtherAccount(postAddOtherAccount: PostAddOtherAccount) {
        _isPostAddOtherAccountSuccess.value = "성공"
    }

}