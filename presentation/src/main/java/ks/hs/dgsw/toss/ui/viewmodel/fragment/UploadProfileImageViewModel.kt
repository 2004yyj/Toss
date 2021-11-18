package ks.hs.dgsw.toss.ui.viewmodel.fragment

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hs.dgsw.domain.usecase.upload.PostUploadImageUseCase
import ks.hs.dgsw.toss.ui.view.util.Event
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class UploadProfileImageViewModel @Inject constructor(
    private val postUploadImageUseCase: PostUploadImageUseCase
): ViewModel() {

    var images: Uri? = null

    private val _isSuccess = MutableLiveData<Event<List<String>>>()
    val isSuccess: LiveData<Event<List<String>>> = _isSuccess

    private val _isFailure = MutableLiveData<Event<String>>()
    val isFailure: LiveData<Event<String>> = _isFailure

    fun postUploadImage(multipartBody: MultipartBody.Part) {
        viewModelScope.launch {
            val params = PostUploadImageUseCase.Params(multipartBody)
            _isSuccess.value = Event(postUploadImageUseCase.buildParamsUseCase(params).files)
        }
    }

}