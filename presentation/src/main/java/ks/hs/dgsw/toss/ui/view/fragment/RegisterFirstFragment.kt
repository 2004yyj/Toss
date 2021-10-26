package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.domain.usecase.user.GetCheckIdUseCase
import ks.hs.dgsw.domain.usecase.user.GetCheckNickUseCase
import ks.hs.dgsw.domain.usecase.user.PostRegisterUseCase
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentRegisterFirstBinding
import ks.hs.dgsw.toss.ui.view.bind.setVisible
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.viewmodel.activity.RegisterViewModel
import ks.hs.dgsw.toss.ui.viewmodel.factory.RegisterViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFirstFragment : Fragment() {

    @Inject
    lateinit var postRegisterUseCase: PostRegisterUseCase
    @Inject
    lateinit var getCheckIdUseCase: GetCheckIdUseCase
    @Inject
    lateinit var getCheckNickUseCase: GetCheckNickUseCase

    private val navController: NavController by lazy { findNavController() }
    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterFirstBinding

    private lateinit var motionLayout: MotionLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterFirstBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity(), RegisterViewModelFactory(
            postRegisterUseCase, getCheckIdUseCase, getCheckNickUseCase
        ))[RegisterViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroy() {
        binding.lifecycleOwner = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
    }

    private fun init() {
        motionLayout = binding.motionLayoutRegisterFirst
    }

    private fun observe() = with(viewModel) {
        motionLayout.transitionToStart()
        registerFirstTitle.value = resources.getString(R.string.register_first_title_input_info)

        name.observe(viewLifecycleOwner) {
            nameError.value =
                when {
                    it.isEmpty() -> resources.getString(R.string.please_set_name)
                    it.length >= 2 -> {
                        if (motionLayout.currentState == R.id.start)
                            motionLayout.transitionToState(R.id.showNickNameLayout)
                        ""
                    }
                    else -> {
                        ""
                    }
                }
        }

        nickname.observe(viewLifecycleOwner) {
            val isNotExist = isNotExistNickname.value?.peekContent()?:false
            if (isNotExist) binding.btnNextRegisterFirst.isEnabled = false
            nicknameError.value =
                if (it.length < 2) resources.getString(R.string.please_set_nickname) else ""
        }

        isNotExistNickname.observe(viewLifecycleOwner, EventObserver { isNot ->
            nicknameError.value = if (isNot) {
                binding.btnNextRegisterFirst.isEnabled = true
                Toast.makeText(context, "사용 가능한 닉네임입니다.", Toast.LENGTH_SHORT).show()
                if (motionLayout.currentState == R.id.showNickNameLayout) {
                    motionLayout.transitionToState(R.id.showSecurityNumLayout)
                    binding.linearLayoutNicknameRegister.setVisible(isNot)
                }
                ""
            } else {
                "중복된 닉네임입니다. 다시 입력해주세요."
            }
        })

        birth.observe(viewLifecycleOwner) {
            birthError.value = if (it.length != 6) {
                resources.getString(R.string.please_set_birth)
            } else {
                ""
            }
        }

        securityCode.observe(viewLifecycleOwner) {
            val regex = Regex("^[1-4]$")
            securityCodeError.value =
                if (!regex.matches(it))
                    resources.getString(R.string.please_set_security_code)
                else {
                    if (it.isNotEmpty() && birth.value?.length?:0 == 6 &&
                        motionLayout.currentState == R.id.showSecurityNumLayout) {
                        motionLayout.transitionToState(R.id.showEmailEditText)
                    }
                    ""
                }
        }

        email.observe(viewLifecycleOwner) {
            val emailChk = Patterns.EMAIL_ADDRESS
            emailError.value =
                if (!emailChk.matcher(it).matches()) {
                    resources.getString(R.string.please_set_email)
                } else {
                    motionLayout.transitionToState(R.id.end)
                    registerFirstTitle.value = resources.getString(R.string.register_title_check_input_info)
                    ""
                }
        }

        isFailure.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        navigateToSecondFragment.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(R.id.action_signUpFirstFragment_to_signUpSecondFragment)
        })
    }

}