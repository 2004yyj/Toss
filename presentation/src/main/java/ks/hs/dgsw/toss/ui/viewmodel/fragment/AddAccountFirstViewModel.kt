package ks.hs.dgsw.toss.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.usecase.account.PostAccountUseCase
import ks.hs.dgsw.domain.usecase.user.GetMyInfoUseCase
import ks.hs.dgsw.toss.ui.view.util.Event
import javax.inject.Inject

@HiltViewModel
class AddAccountFirstViewModel @Inject constructor(
    private val getMyInfoUseCase: GetMyInfoUseCase
): ViewModel() {

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

    private val _isFailure = MutableLiveData<Event<String>>()
    val isFailure: LiveData<Event<String>> = _isFailure

    fun checkIsItValidate() {
        val name = name.value?:""
        val securityNumberFirst = securityNumberFirst.value?:""
        val securityNumberSecond = securityNumberSecond.value?:""
        val phone = (phone.value?:"").replace("-", "")

        viewModelScope.launch {
            if (name.isNotEmpty() && securityNumberFirst.isNotEmpty() &&
                securityNumberSecond.isNotEmpty() && phone.isNotEmpty()) {
                    val user = getMyInfoUseCase.buildUseCase()

                    Log.d("AddAccountFirstViewModel", "checkIsItValidate: $user")

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

}