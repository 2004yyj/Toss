package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
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
import ks.hs.dgsw.domain.usecase.user.PostRegisterUseCase
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentRegisterFirstBinding
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.viewmodel.activity.RegisterViewModel
import ks.hs.dgsw.toss.ui.viewmodel.factory.RegisterViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFirstFragment : Fragment() {

    @Inject
    lateinit var postRegisterUseCase: PostRegisterUseCase

    private val navController: NavController by lazy { findNavController() }
    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterFirstBinding

    private lateinit var motionLayout: MotionLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterFirstBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity(), RegisterViewModelFactory(postRegisterUseCase))[RegisterViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
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
            nicknameError.value = if (it.length < 2)
                resources.getString(R.string.please_set_nickname)
            else {
                if (motionLayout.currentState == R.id.showNickNameLayout)
                    motionLayout.transitionToState(R.id.showSecurityNumLayout)
                ""
            }
        }

        birth.observe(viewLifecycleOwner) {
            birthError.value = when (it.length == 6) {
                true -> {
                    if (it.isNotEmpty() && securityCode.value?.isNotEmpty() == true &&
                        motionLayout.currentState == R.id.showSecurityNumLayout) {
                        motionLayout.transitionToState(R.id.showEmailEditText)
                    }
                    ""
                }
                false -> resources.getString(R.string.please_set_birth)
            }
        }

        securityCode.observe(viewLifecycleOwner) {
            val regex = Regex("^[1-4]$")
            securityCodeError.value =
                if (it.length != 1 || !regex.matches(it))
                    resources.getString(R.string.please_set_security_code)
                else {
                    if (it.isNotEmpty() && birth.value?.isNotEmpty() == true &&
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