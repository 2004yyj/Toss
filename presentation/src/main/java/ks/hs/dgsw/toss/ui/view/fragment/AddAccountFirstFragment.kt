package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentAddAccountFirstBinding
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.viewmodel.fragment.AddAccountViewModel

@AndroidEntryPoint
class AddAccountFirstFragment : Fragment() {

    private lateinit var binding: FragmentAddAccountFirstBinding
    private val viewModel: AddAccountViewModel by activityViewModels()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddAccountFirstBinding.inflate(inflater)
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

    private fun observe() = with(viewModel) {
        isSuccess.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(R.id.action_addAccountFirstFragment_to_addAccountSecondFragment)
        })

        isFailure.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

             accountName.observe(viewLifecycleOwner) {
            accountNameError.value = if (it.isEmpty()) {
                "계좌 이름을 입력해 주세요."
            } else {
                if (binding.motionLayoutAddAccount.currentState == R.id.start)
                    binding.motionLayoutAddAccount.transitionToState(R.id.showNameLayout)
                ""
            }
        }

        name.observe(viewLifecycleOwner) {
            nameError.value = if (it.isEmpty()) {
                resources.getString(R.string.please_set_name)
            } else {
                if (binding.motionLayoutAddAccount.currentState == R.id.showNameLayout)
                    binding.motionLayoutAddAccount.transitionToState(R.id.showSecurityNumsLayout)
                ""
            }
        }

        securityNumberFirst.observe(viewLifecycleOwner) {
            securityNumberFirstError.value = when (it.length == 6) {
                true -> ""
                false -> resources.getString(R.string.please_set_birth)
            }
        }

        securityNumberSecond.observe(viewLifecycleOwner) {
            securityNumberSecondError.value = when (it.length == 7) {
                true -> {
                    if (securityNumberFirst.value?.length == 6 &&
                        binding.motionLayoutAddAccount.currentState == R.id.showSecurityNumsLayout) {
                        binding.motionLayoutAddAccount.transitionToState(R.id.showPhoneNumberLayout)
                    }
                    ""
                }
                false -> resources.getString(R.string.please_set_security_number)
            }
        }

        phone.observe(viewLifecycleOwner) {
            val phoneRegex = Regex("^\\d{3}-\\d{3,4}-\\d{4}\$")
            phoneError.value = if (!phoneRegex.matches(it)) {
                "전화번호 형식에 맞게 입력해 주세요."
            } else {
                if (binding.motionLayoutAddAccount.currentState == R.id.showPhoneNumberLayout)
                    binding.motionLayoutAddAccount.transitionToState(R.id.end)
                ""
            }
        }
    }

    private fun init() = with(binding) {
        vm = viewModel
        etPhoneRegister.addTextChangedListener(PhoneNumberFormattingTextWatcher("KR"))

        toolbarAccountPassword.setNavigationOnClickListener {
            finishActivity()
        }

        requireActivity().onBackPressedDispatcher.addCallback(object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishActivity()
            }
        })
    }

    private fun finishActivity() {
        requireActivity().finish()
        requireActivity().overridePendingTransition(R.anim.pop_slide_in_left, R.anim.pop_slide_out_right)
    }



}