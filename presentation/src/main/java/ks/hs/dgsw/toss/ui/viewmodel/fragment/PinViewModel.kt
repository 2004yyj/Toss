package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.dto.Token
import ks.hs.dgsw.domain.entity.request.Password
import ks.hs.dgsw.domain.usecase.password.PostPasswordLoginUseCase
import ks.hs.dgsw.domain.usecase.password.PostPasswordRegisterUseCase

class PinViewModel(
    private val postPasswordRegisterUseCase: PostPasswordRegisterUseCase,
    private val postPasswordLoginUseCase: PostPasswordLoginUseCase
): ViewModel() {

    val password = MutableLiveData<String>()

    private val _isFailure = MutableLiveData<String>()
    val isFailure: LiveData<String> = _isFailure

    private val _isRegisterSuccess = MutableLiveData<String>()
    val isRegisterSuccess: LiveData<String> = _isRegisterSuccess

    private val _isLoginSuccess = MutableLiveData<Token>()
    val isLoginSuccess: LiveData<Token> = _isLoginSuccess

    fun postPasswordRegister() {
        val password = password.value?:""
        val passwordBody = Password(password)

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
        val passwordBody = Password(password)

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