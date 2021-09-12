package ks.hs.dgsw.toss.ui.viewmodel.activity

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ks.hs.dgsw.toss.ui.view.util.Event

class RegisterViewModel: ViewModel() {
    // 실패 시 메시지를 보내는 라이브 데이터 객체
    private val _isFailure = MutableLiveData<Event<String>>()
    val isFailure: LiveData<Event<String>> = _isFailure

    // 성공 시 1단계->2단계로 프래그먼트를 전환하는 라이브 데이터 객체
    private val _navigateToSecondFragment = MutableLiveData<Event<String>>()
    val navigateToSecondFragment: LiveData<Event<String>> = _navigateToSecondFragment

    // 회원가입 1단계 프래그먼트 데이터
    val name = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val birth = MutableLiveData<String>()
    val securityCode = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    // 회원가입 1단계 에러 텍스트 표시용
    val nameError = MutableLiveData("")
    val nicknameError = MutableLiveData("")
    val birthError = MutableLiveData("")
    val securityCodeError = MutableLiveData("")
    val emailError = MutableLiveData("")

    // 회원가입 2단계 프래그먼트 데이터
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val pwCheck = MutableLiveData<String>()
    val phone = MutableLiveData<String>()

    // 회원가입 2단계 에러 텍스트 표시용
    val idError = MutableLiveData("")
    val pwError = MutableLiveData("")
    val pwCheckError = MutableLiveData("")
    val phoneError = MutableLiveData("")

    fun toSecondFragment() { // 1단계에서 2단계 이동 시 예외 처리
        val name = name.value
        val nickname = nickname.value
        val birth = birth.value
        val securityCode = securityCode.value
        val email = email.value

        val emailChecker = Patterns.EMAIL_ADDRESS

        val nameAuthChk = name != null && name.isNotEmpty()
        val nicknameAuthChk = nickname != null && nickname.length >= 2
        val birthAuthChk = birth != null && birth.length == 6
        val securityCodeAuthChk = securityCode != null && securityCode.isNotEmpty()
        val emailAuthChk = email != null && emailChecker.matcher(email).matches()

        if (nameAuthChk && nicknameAuthChk
            && birthAuthChk && securityCodeAuthChk
            && emailAuthChk) {
            _navigateToSecondFragment.value = Event("toSignUpSecondFragment")
        } else {
            _isFailure.value = Event("입력하지 않은 값이나 잘못된 값이 없는지 확인해 주세요.")
        }
    }
}