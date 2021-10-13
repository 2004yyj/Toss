package ks.hs.dgsw.toss.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.dto.PasswordLoginToken
import ks.hs.dgsw.domain.entity.request.PasswordLogin
import ks.hs.dgsw.domain.entity.request.PasswordRegister
import ks.hs.dgsw.domain.usecase.password.PostPasswordLoginUseCase
import ks.hs.dgsw.domain.usecase.password.PostPasswordRegisterUseCase
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.passwordLoginId
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PinViewModel @Inject constructor(
    private val postPasswordRegisterUseCase: PostPasswordRegisterUseCase,
    private val postPasswordLoginUseCase: PostPasswordLoginUseCase
): ViewModel() {

    val keyPadArray = MutableLiveData(Array(10) { it.toString() })

    val password = MutableLiveData("")

    private val _isFailure = MutableLiveData<String>()
    val isFailure: LiveData<String> = _isFailure

    private val _isRegisterSuccess = MutableLiveData<String>()
    val isRegisterSuccess: LiveData<String> = _isRegisterSuccess

    private val _isLoginSuccess = MutableLiveData<PasswordLoginToken>()
    val isLoginSuccess: LiveData<PasswordLoginToken> = _isLoginSuccess

    init { keyPadArray.value?.shuffle() }

    fun setKeyNumber(number: String) {
        if (password.value?.length?.plus(1) ?: 0  <= 6) {
            password.value = password.value!! + number
            Log.d("TAG", "setKeyNumber: ${password.value}")
        }
    }

    fun backspaceNumber() {
        password.value = if (password.value?.length!! <= 1) {
            ""
        } else {
            password.value?.substring(0 until password.value?.lastIndex!!)
        }
    }

    fun postPasswordRegister() {
        val password = password.value?:""
        val passwordBody = PasswordRegister(password)

        if (password.isNotEmpty()) {
            viewModelScope.launch {
                val params = PostPasswordRegisterUseCase.Params(passwordBody)

                try {
                    _isRegisterSuccess.value =
                        postPasswordRegisterUseCase.buildParamsUseCase(params)
                } catch (e: Throwable) {
                    _isFailure.value = e.message
                }
            }
        } else {
            _isFailure.value = "비밀번호가 비어 있습니다."
        }
    }

    fun postPasswordLogin() {
        val password = password.value?:""
        val passwordBody = PasswordLogin(passwordLoginId?:"", password)

        if (password.isNotEmpty()) {
            viewModelScope.launch {
                val params = PostPasswordLoginUseCase.Params(passwordBody)

                try {
                    _isLoginSuccess.value =
                        postPasswordLoginUseCase.buildParamsUseCase(params)
                } catch (e: Throwable) {
                    _isFailure.value = e.message
                }
            }
        } else {
            _isFailure.value = "비밀번호가 비어 있습니다."
        }
    }

}