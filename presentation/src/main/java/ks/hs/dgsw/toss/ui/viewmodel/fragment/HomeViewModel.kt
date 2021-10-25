package ks.hs.dgsw.toss.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.usecase.account.GetAccountsByTokenUseCase
import ks.hs.dgsw.domain.usecase.user.GetMyInfoUseCase
import ks.hs.dgsw.toss.ui.view.util.Event
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMyInfoUseCase: GetMyInfoUseCase,
    private val getAccountsByTokenUseCase: GetAccountsByTokenUseCase,
): ViewModel() {
    val name = MutableLiveData<String>()
    val accountList = MutableLiveData(ArrayList<Account>())

    private val _openRemitPage = MutableLiveData<Event<String>>()
    val openRemitPage: LiveData<Event<String>> = _openRemitPage

    private val _isFailure = MutableLiveData<Event<String>>()
    val isFailure: LiveData<Event<String>> = _isFailure

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                getMyInfoUseCase.buildUseCase().apply {
                    this@HomeViewModel.name.value = name
                }
                getAccountsByTokenUseCase.buildUseCase().apply {
                    accountList.value!!.clear()
                    accountList.value!!.addAll(accounts)
                }
            } catch (e: Throwable) {
                _isFailure.value = Event(e.message?:"")
            }
        }
    }

    fun remitPage() {
        _openRemitPage.value = Event("openRemitPage")
    }

}