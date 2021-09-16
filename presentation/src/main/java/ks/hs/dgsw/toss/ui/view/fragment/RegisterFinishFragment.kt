package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.HiltAndroidApp
import ks.hs.dgsw.domain.usecase.user.PostLoginUseCase
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentRegisterFinishBinding
import ks.hs.dgsw.toss.ui.viewmodel.fragment.LoginViewModel
import ks.hs.dgsw.toss.ui.viewmodel.factory.LoginViewModelFactory
import javax.inject.Inject

@HiltAndroidApp
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
            val bundle = Bundle()
            bundle.putString("token", it)
            navController.navigate(R.id.action_registerFinishFragment_to_setPinFragment, bundle)
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
                login()
            }
        }
    }

    private fun init() {
        id = arguments?.get("id") as String
        pw = arguments?.get("pw") as String
        btnSetPin = binding.btnSetPinFinish
    }
}