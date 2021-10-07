package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import ks.hs.dgsw.domain.entity.dto.LoginToken
import ks.hs.dgsw.domain.entity.request.Login
import ks.hs.dgsw.domain.usecase.user.PostLoginUseCase

class LoginViewModel(
    private val postLoginUseCase: PostLoginUseCase
): ViewModel() {

    private val _isSuccess = MutableLiveData<LoginToken>()
    val isSuccess: LiveData<LoginToken> = _isSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure: LiveData<String> = _isFailure

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun login() {
        val id = id.value?:""
        val pw = pw.value?:""

        viewModelScope.launch {
            val login = Login(id, pw)
            try {
                withTimeout(15000) {
                    _isSuccess.value = postLoginUseCase.buildParamsUseCase(PostLoginUseCase.Params(login))
                }
            } catch (e: Throwable) {
                failure(e.message?:"")
            } catch (e: TimeoutCancellationException) {
                failure("시간이 초과되었습니다.")
            }
        }
    }

    private fun failure(message: String) {
        _isFailure.value = message
    }
}