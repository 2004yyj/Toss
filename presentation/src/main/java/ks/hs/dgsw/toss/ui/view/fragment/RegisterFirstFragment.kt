package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentRegisterFirstBinding
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.viewmodel.activity.RegisterViewModel
import ks.hs.dgsw.toss.ui.viewmodel.factory.RegisterViewModelFactory

class RegisterFirstFragment : Fragment() {

    private val navController: NavController by lazy { findNavController() }
    private val factory by lazy { RegisterViewModelFactory() }
    private val viewModel: RegisterViewModel by activityViewModels { factory }
    private lateinit var binding: FragmentRegisterFirstBinding
    private lateinit var motionLayout: MotionLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterFirstBinding.inflate(inflater)
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
        motionLayout = binding.motionLayoutRegister
    }

    private fun observe() = with(viewModel) {
        motionLayout.transitionToStart()

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
                    if (it.length + birth.value?.length!! == 7 &&
                        motionLayout.currentState == R.id.showSecurityNumLayout) {
                        motionLayout.transitionToState(R.id.showEmailEditText)
                    }
                    ""
                }
                false -> resources.getString(R.string.please_set_birth)
            }
        }

        securityCode.observe(viewLifecycleOwner) {
            securityCodeError.value =
                if (it.length != 1)
                    resources.getString(R.string.please_set_security_code)
                else {
                    if (it.length + birth.value?.length!! == 7 &&
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