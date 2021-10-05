package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import ks.hs.dgsw.domain.usecase.user.PostLoginUseCase
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentRegisterFinishBinding
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.token
import ks.hs.dgsw.toss.ui.viewmodel.fragment.LoginViewModel
import ks.hs.dgsw.toss.ui.viewmodel.factory.LoginViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFinishFragment : Fragment() {

    @Inject
    lateinit var postLoginUseCase: PostLoginUseCase

    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentRegisterFinishBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var btnSetPin: Button

    private lateinit var id: String
    private lateinit var pw: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterFinishBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(postLoginUseCase))[LoginViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        listener()
        observe()
    }

    private fun observe() = with(viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            token = it.token
            navController.navigate(R.id.action_registerFinishFragment_to_setPinFragment)
        }

        isFailure.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun listener() {
        btnSetPin.setOnClickListener {
            with(viewModel) {
                id.value = this@RegisterFinishFragment.id
                pw.value = this@RegisterFinishFragment.pw
                Log.d("RegisterFinishFragment", "listener: $id")
                Log.d("RegisterFinishFragment", "listener: $pw")
                login()
            }
        }
    }

    private fun init() {
        id = arguments?.getString("id")?:""
        pw = arguments?.getString("pw")?:""
        btnSetPin = binding.btnSetPinFinish
    }
}