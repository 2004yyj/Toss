package ks.hs.dgsw.toss.ui.viewmodel.fragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.usecase.user.GetMyInfoUseCase
import ks.hs.dgsw.toss.ui.view.util.Event
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMyInfoUseCase: GetMyInfoUseCase
): ViewModel() {
    val nick = MutableLiveData<String>()
    val accountList = MutableLiveData(ArrayList<Account>())

    private val _openRemitPage = MutableLiveData<Event<String>>()
    val openRemitPage = _openRemitPage

    fun getUserInfo() {
        viewModelScope.launch {
            getMyInfoUseCase.buildUseCase().apply {
                accountList.value!!.clear()
                accountList.value!!.addAll(
                    if (account.size <= 2) {
                        account
                    } else {
                        account.subList(0, 2)
                    }
                )
                this@HomeViewModel.nick.value = nick
            }
        }
    }

    fun remitPage() {
        _openRemitPage.value = Event("openRemitPage")
    }

}