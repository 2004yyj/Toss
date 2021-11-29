package ks.hs.dgsw.toss.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.domain.usecase.user.PostLoginUseCase
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentLoginBinding
import ks.hs.dgsw.toss.ui.view.activity.MainActivity
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.loginToken
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.passwordLoginId
import ks.hs.dgsw.toss.ui.viewmodel.factory.LoginViewModelFactory
import ks.hs.dgsw.toss.ui.viewmodel.fragment.LoginViewModel
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var postLoginUseCase: PostLoginUseCase

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var motionLayout: MotionLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(postLoginUseCase))[LoginViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroy() {
        binding.lifecycleOwner = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewModel) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
    }

    private fun observe() = with(viewModel) {
        id.observe(viewLifecycleOwner) {
            idError.value = if (it.isEmpty())
                "아이디를 입력해주세요."
            else {
                if (motionLayout.currentState == R.id.start) {
                    motionLayout.transitionToState(R.id.showPwLayout)
                    viewModel.loginTitle.value = "비밀번호를 입력해주세요"
                }
                ""
            }
        }

        pw.observe(viewLifecycleOwner) {
            pwError.value = if (it.isEmpty()) {
                "비밀번호를 입력해주세요."
            } else {
                if (motionLayout.currentState == R.id.showPwLayout) {
                    motionLayout.transitionToState(R.id.showPhoneNumberLayout)
                    viewModel.loginTitle.value = "입력한 값이 맞는지 확인해주세요."
                }
                ""
            }
        }

        isSuccess.observe(viewLifecycleOwner) {
            loginToken = it.token
            passwordLoginId = it.simpleId

            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        isFailure.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun init() {
        viewModel.loginTitle.value = "아이디를 입력해주세요."
        motionLayout = binding.motionLayoutLogin
    }
}