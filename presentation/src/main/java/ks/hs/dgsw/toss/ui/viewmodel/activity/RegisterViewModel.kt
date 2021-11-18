package ks.hs.dgsw.toss.ui.viewmodel.activity

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import ks.hs.dgsw.domain.entity.dto.RegisterToken
import ks.hs.dgsw.domain.entity.request.Register
import ks.hs.dgsw.domain.usecase.user.GetCheckIdUseCase
import ks.hs.dgsw.domain.usecase.user.GetCheckNickUseCase
import ks.hs.dgsw.domain.usecase.user.PostRegisterUseCase
import ks.hs.dgsw.toss.ui.view.util.Event

class RegisterViewModel constructor(
    private val postRegisterUseCase: PostRegisterUseCase,
    private val getCheckIdUseCase: GetCheckIdUseCase,
    private val getCheckNickUseCase: GetCheckNickUseCase
): ViewModel() {
    var profileImage: String = ""

    // 실패 시 메시지를 보내는 라이브 데이터 객체
    private val _isFailure = MutableLiveData<Event<String>>()
    val isFailure: LiveData<Event<String>> = _isFailure

    // 성공 시 데이터를 보내는 라이브 데이터 객체
    private val _isSuccess = MutableLiveData<Event<RegisterToken>>()
    val isSuccess: LiveData<Event<RegisterToken>> = _isSuccess

    // 성공 시 1단계->2단계로 프래그먼트를 전환하는 라이브 데이터 객체
    private val _navigateToSecondFragment = MutableLiveData<Event<String>>()
    val navigateToSecondFragment: LiveData<Event<String>> = _navigateToSecondFragment

    // 회원가입 1단계 프래그먼트 타이틀
    val registerFirstTitle = MutableLiveData<String>()

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

    // 회원가입 2단계 프래그먼트 타이틀
    val registerSecondTitle = MutableLiveData<String>()

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

    private val _isNotExistNickname = MutableLiveData<Event<Boolean>>()
    val isNotExistNickname: LiveData<Event<Boolean>> = _isNotExistNickname

    private val _isNotExistId = MutableLiveData<Event<Boolean>>()
    val isNotExistId: LiveData<Event<Boolean>> = _isNotExistId

    fun toSecondFragment() { // 1단계에서 2단계 이동 시 예외 처리
        val regex = Regex("^[1-4]$")
        val name = name.value
        val nickname = nickname.value
        val birth = birth.value
        val securityCode = securityCode.value
        val email = email.value

        val emailChecker = Patterns.EMAIL_ADDRESS

        val nameAuthChk = name != null && name.isNotEmpty()
        val nicknameAuthChk = nickname != null && nickname.length >= 2
        val birthAuthChk = birth != null && birth.length == 6 && regex.matches(securityCode?:"")
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

    fun chkNicknameExist() {
        val nickname = nickname.value?:""

        if (nickname.isNotEmpty()) {
            viewModelScope.launch {
                val params = GetCheckNickUseCase.Params(nickname)
                val exist = Event(getCheckNickUseCase.buildParamsUseCase(params))
                _isNotExistNickname.value = exist
            }
        } else {
            _isFailure.value = Event("닉네임이 비어 있습니다.")
        }
    }

    fun chkIdExist() {
        val id = id.value?:""

        if (id.length !in 3..12) {
            _isFailure.value = Event("아이디는 3-12 이내로 입력해주세요.")
        } else if (id.isNotEmpty()) {
            viewModelScope.launch {
                val params = GetCheckIdUseCase.Params(id)
                val exist = Event(getCheckIdUseCase.buildParamsUseCase(params))
                _isNotExistId.value = exist
            }
        } else {
            _isFailure.value = Event("아이디가 비어 있습니다.")
        }
    }

    fun toFinishFragment() {
        val id = id.value?:""
        val pw = pw.value?:""
        val nickname = nickname.value?:""
        val name = name.value?:""
        val phone = phone.value?:""
        val securityCode = securityCode.value?:""
        val pwCheck = pwCheck.value?:""
        var birth = birth.value?:""

        viewModelScope.launch {
            if (id.isNotEmpty() && pw.isNotEmpty() && pw == pwCheck && phone.isNotEmpty() &&
            (idError.value?:"").isEmpty() && (pwError.value?:"").isEmpty() && (phoneError.value?:"").isEmpty()) {
                birth = if (securityCode == "1" || securityCode == "2") {
                    "19$birth"
                } else {
                    "20$birth"
                }

                val unformattedPhoneNumber = phone.replace("-", "")
                val register = Register(id, pw, nickname, name, unformattedPhoneNumber, birth, profileImage)
                try {
                    withTimeout(10000) {
                        val registerToken = postRegisterUseCase.buildParamsUseCase(PostRegisterUseCase.Params(register))
                        _isSuccess.value = Event(registerToken)
                    }
                } catch (e: Throwable) {
                    _isFailure.value = Event(e.message?:"")
                } catch (e: TimeoutCancellationException) {
                    _isFailure.value = Event("시간이 초과되었습니다.")
                }
            }
        }
    }
}