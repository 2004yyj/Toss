package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.domain.usecase.user.GetCheckIdUseCase
import ks.hs.dgsw.domain.usecase.user.GetCheckNickUseCase
import ks.hs.dgsw.domain.usecase.user.PostRegisterUseCase
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentRegisterSecondBinding
import ks.hs.dgsw.toss.ui.view.bind.setVisible
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.registerToken
import ks.hs.dgsw.toss.ui.viewmodel.activity.RegisterViewModel
import ks.hs.dgsw.toss.ui.viewmodel.factory.RegisterViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class RegisterSecondFragment : Fragment() {

    @Inject
    lateinit var postRegisterUseCase: PostRegisterUseCase
    @Inject
    lateinit var getCheckIdUseCase: GetCheckIdUseCase
    @Inject
    lateinit var getCheckNickUseCase: GetCheckNickUseCase

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

        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            NavigationUI.setupWithNavController(toolbar, navController)
            supportActionBar?.title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
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
            val idRegex = Regex("^(?=.*[0-9]+)[a-z][a-z0-9]{3,12}\$")
            val isNotExist = isNotExistId.value?.peekContent() ?: false
            if (isNotExist) binding.btnNextRegisterSecond.isEnabled = false
            idError.value =
                if (!idRegex.matches(it))
                    resources.getString(R.string.please_set_id) else ""
        }

        isNotExistId.observe(viewLifecycleOwner, EventObserver { isNot ->
            idError.value = if (isNot) {
                binding.btnNextRegisterSecond.isEnabled = true
                Toast.makeText(context, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                if (motionLayout.currentState == R.id.start) {
                    motionLayout.transitionToState(R.id.showPwLayout)
                    binding.etPwRegister.setVisible(true)
                }
                ""
            } else {
                "중복된 아이디입니다. 다시 입력해주세요."
            }
        })

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
                    motionLayout.transitionToState(R.id.showPhoneNumberLayout)
                registerSecondTitle.value = resources.getString(R.string.register_title_check_input_info)
                ""
            }
        }

        isSuccess.observe(viewLifecycleOwner, EventObserver {
            registerToken = it.token
            Log.d("RegisterSecondFragment", "observe: $registerToken")
            val bundle = Bundle()
            bundle.putString("id", id.value?:"")
            bundle.putString("pw", pw.value?:"")
            navController.navigate(R.id.action_registerSecondFragment_to_registerFinishFragment, bundle)
        })

        isFailure.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }
}