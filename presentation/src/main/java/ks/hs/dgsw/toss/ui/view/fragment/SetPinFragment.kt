package ks.hs.dgsw.toss.ui.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.domain.usecase.password.PostPasswordLoginUseCase
import ks.hs.dgsw.domain.usecase.password.PostPasswordRegisterUseCase
import ks.hs.dgsw.domain.usecase.user.PostLoginUseCase
import ks.hs.dgsw.toss.databinding.FragmentSetPinBinding
import ks.hs.dgsw.toss.ui.view.activity.MainActivity
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.loginToken
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.passwordLoginId
import ks.hs.dgsw.toss.ui.viewmodel.factory.LoginViewModelFactory
import ks.hs.dgsw.toss.ui.viewmodel.factory.PinViewModelFactory
import ks.hs.dgsw.toss.ui.viewmodel.fragment.LoginViewModel
import ks.hs.dgsw.toss.ui.viewmodel.fragment.PinViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SetPinFragment : Fragment() {

    @Inject
    lateinit var postPasswordRegisterUseCase: PostPasswordRegisterUseCase

    @Inject
    lateinit var postPasswordLoginUseCase: PostPasswordLoginUseCase

    @Inject
    lateinit var postLoginUseCase: PostLoginUseCase

    private lateinit var binding: FragmentSetPinBinding
    private lateinit var pinViewModel: PinViewModel
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetPinBinding.inflate(inflater)

        val pinViewModelFactory = PinViewModelFactory(postPasswordRegisterUseCase, postPasswordLoginUseCase)
        val loginViewModelFactory = LoginViewModelFactory(postLoginUseCase)
        pinViewModel = ViewModelProvider(this, pinViewModelFactory)[PinViewModel::class.java]
        loginViewModel = ViewModelProvider(this, loginViewModelFactory)[LoginViewModel::class.java]

        binding.vm = pinViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onDestroy() {
        binding.lifecycleOwner = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        binding.otpPasswordSetPin.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.otpPasswordSetPin.windowToken, 0)
            }
        }
    }

    private fun observe() = with(pinViewModel) {

        isFailure.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        isRegisterSuccess.observe(viewLifecycleOwner) {
            loginViewModel.id.value = requireArguments().getString("id", "")
            loginViewModel.pw.value = requireArguments().getString("pw", "")
            loginViewModel.login()
        }

        loginViewModel.isSuccess.observe(viewLifecycleOwner) {
            loginToken = it.token
            passwordLoginId = it.simpleId

            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}