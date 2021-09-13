package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.domain.usecase.user.PostRegisterUseCase
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentRegisterSecondBinding
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.viewmodel.activity.RegisterViewModel
import ks.hs.dgsw.toss.ui.viewmodel.factory.RegisterViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class RegisterSecondFragment : Fragment() {

    @Inject
    lateinit var postRegisterUseCase: PostRegisterUseCase

    private val navController by lazy { findNavController() }
    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterSecondBinding

    private lateinit var toolbar: Toolbar
    private lateinit var motionLayout: MotionLayout
    private lateinit var etPhone: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterSecondBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity(), RegisterViewModelFactory(postRegisterUseCase))[RegisterViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observe()

        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.title = ""
            NavigationUI.setupWithNavController(toolbar, navController)
        }
    }

    private fun init() {
        toolbar = binding.toolbarRegisterSecond
        motionLayout = binding.motionLayoutRegisterSecond
        etPhone = binding.etPhoneRegister
        etPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher("KR"))
    }

    private fun observe() = with(viewModel) {
        motionLayout.transitionToStart()
        registerSecondTitle.value = resources.getString(R.string.register_second_title_input_info)

        id.observe(viewLifecycleOwner) {
            idError.value = if (it.length !in 3..12)
                resources.getString(R.string.please_set_id)
            else {
                if (motionLayout.currentState == R.id.start)
                    motionLayout.transitionToState(R.id.showPwLayout)
                ""
            }
        }

        pw.observe(viewLifecycleOwner) {
            val pwRegex = Regex("^.*(?=^.{8,12}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$")
            pwError.value = if (!pwRegex.matches(it)) {
                resources.getString(R.string.please_set_pw)
            } else {
                if (motionLayout.currentState == R.id.showPwLayout)
                    motionLayout.transitionToState(R.id.showPwCheckLayout)
                ""
            }
        }

        pwCheck.observe(viewLifecycleOwner) {
            pwCheckError.value = if (it != pw.value) {
                resources.getString(R.string.password_is_not_equal)
            } else {
                if (motionLayout.currentState == R.id.showPwCheckLayout)
                    motionLayout.transitionToState(R.id.showPhoneNumLayout)
                ""
            }
        }

        phone.observe(viewLifecycleOwner) {
            val phoneRegex = Regex("^\\d{3}-\\d{3,4}-\\d{4}\$")
            phoneError.value = if (!phoneRegex.matches(it)) {
                "전화번호 형식에 맞게 입력해 주세요."
            } else {
                if (motionLayout.currentState == R.id.showPhoneNumLayout)
                    motionLayout.transitionToState(R.id.end)
                registerSecondTitle.value = resources.getString(R.string.register_title_check_input_info)
                ""
            }
        }

        isSuccessRegister.observe(viewLifecycleOwner, EventObserver {
            Log.d("RegisterSecondFragment", "observe: $it")
        })

        isFailure.observe(viewLifecycleOwner, EventObserver {
            Log.d("RegisterSecondFragment", "observe: $it")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }

}