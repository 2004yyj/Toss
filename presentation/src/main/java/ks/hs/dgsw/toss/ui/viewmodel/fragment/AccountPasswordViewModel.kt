package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.request.SendMoney
import ks.hs.dgsw.domain.usecase.transfer.PostSendMoneyUseCase
import ks.hs.dgsw.toss.ui.view.util.Event
import javax.inject.Inject

@HiltViewModel
class AccountPasswordViewModel @Inject constructor(
    private val postSendMoneyUseCase: PostSendMoneyUseCase
): ViewModel() {
    val keyPadArray = MutableLiveData(Array(10) { it.toString() })
    val password = MutableLiveData("")
    init { keyPadArray.value?.shuffle() }

    private val _isSuccess = MutableLiveData<Event<String>>()
    val isSuccess: LiveData<Event<String>> = _isSuccess

    private val _isFailure = MutableLiveData<Event<String>>()
    val isFailure: LiveData<Event<String>> = _isFailure

    fun postSendMoney(sendMoney: SendMoney) {
        viewModelScope.launch {
            try {
                val params = PostSendMoneyUseCase.Params(sendMoney)
                _isSuccess.value = Event(postSendMoneyUseCase.buildParamsUseCase(params))
            } catch (e: Throwable) {
                _isFailure.value = Event(e.message ?: "")
            }
        }

    }

    fun setKeyNumber(number: String) {
        if (password.value?.length?.plus(1) ?: 0  <= 4) {
            password.value = password.value!! + number
        }
    }

    fun backspaceNumber() {
        password.value = if (password.value?.length!! <= 1) {
            ""
        } else {
            password.value?.substring(0 until password.value?.lastIndex!!)
        }
    }

}