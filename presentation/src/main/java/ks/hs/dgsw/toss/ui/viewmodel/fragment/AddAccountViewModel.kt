package ks.hs.dgsw.toss.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.request.PostAccount
import ks.hs.dgsw.domain.usecase.account.PostAccountUseCase
import ks.hs.dgsw.domain.usecase.user.GetMyInfoUseCase
import ks.hs.dgsw.toss.ui.view.util.Event
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val getMyInfoUseCase: GetMyInfoUseCase,
    private val postAccountUseCase: PostAccountUseCase
): ViewModel() {

    val keyPadArray = MutableLiveData(Array(10) { it.toString() })
    val password = MutableLiveData("")
    init { keyPadArray.value?.shuffle() }

    val name = MutableLiveData<String>()
    val nameError = MutableLiveData("")

    val securityNumberFirst = MutableLiveData<String>()
    val securityNumberFirstError = MutableLiveData("")

    val securityNumberSecond = MutableLiveData<String>()
    val securityNumberSecondError = MutableLiveData("")

    val phone = MutableLiveData<String>()
    val phoneError = MutableLiveData("")

    private val _isSuccess = MutableLiveData<Event<String>>()
    val isSuccess: LiveData<Event<String>> = _isSuccess

    private val _isSuccessAddAccount = MutableLiveData<Event<String>>()
    val isSuccessAddAccount: LiveData<Event<String>> = _isSuccessAddAccount

    private val _isFailure = MutableLiveData<Event<String>>()
    val isFailure: LiveData<Event<String>> = _isFailure

    fun setKeyNumber(number: String) {
        if (password.value?.length?.plus(1) ?: 0  <= 6) {
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

    fun checkIsItValidate() {
        val name = name.value?:""
        var securityNumberFirst = securityNumberFirst.value?:""
        val securityNumberSecond = securityNumberSecond.value?:""
        val phone = (phone.value?:"").replace("-", "")

        viewModelScope.launch {
            if (name.isNotEmpty() && securityNumberFirst.isNotEmpty() &&
                securityNumberSecond.isNotEmpty() && phone.isNotEmpty()) {
                    val user = getMyInfoUseCase.buildUseCase()
                    securityNumberFirst =
                        if (securityNumberSecond[0] == '1' || securityNumberSecond[0] == '2') {
                            "19$securityNumberFirst"
                        } else {
                            "20$securityNumberFirst"
                        }
                        if (securityNumberFirst == user.birth && phone == user.phone) {
                            _isSuccess.value = Event("성공")
                        } else {
                            _isFailure.value = Event("값이 맞는지 다시 한번 확인해 주세요.")
                        }
            } else {
                _isFailure.value = Event("입력되지 않은 값이 있는지 확인해주세요.")
            }
        }
    }

    fun postAccount() {
        val name = name.value ?: ""
        val securityNumberSecond = securityNumberSecond.value ?: ""
        val securityNumberFirst =
            if (securityNumberSecond[0] == '1' || securityNumberSecond[0] == '2') {
                "19${securityNumberFirst.value?:""}"
            } else {
                "20${securityNumberFirst.value?:""}"
            }
        val password = password.value ?: ""

        if (password.isNotEmpty()) {
            val postAccountBody = PostAccount(name, securityNumberFirst, password)
            Log.d("AddAccountViewModel", "postAccount: $postAccountBody")
            viewModelScope.launch {
                val params = PostAccountUseCase.Params(postAccountBody)
                try {
                    _isSuccessAddAccount.value =
                        Event(postAccountUseCase.buildParamsUseCase(params).account)
                } catch (e: Throwable) {
                    _isFailure.value = Event(e.message ?: "")
                }
            }
        } else {
            _isFailure.value = Event("비밀번호가 비어 있습니다.")
        }
    }

}