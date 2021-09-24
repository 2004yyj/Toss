package ks.hs.dgsw.toss.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import ks.hs.dgsw.domain.usecase.password.PostPasswordLoginUseCase
import ks.hs.dgsw.domain.usecase.password.PostPasswordRegisterUseCase
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentSetPinBinding
import ks.hs.dgsw.toss.ui.view.activity.MainActivity
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.token
import ks.hs.dgsw.toss.ui.viewmodel.factory.PinViewModelFactory
import ks.hs.dgsw.toss.ui.viewmodel.fragment.PinViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SetPinFragment : Fragment() {

    @Inject
    lateinit var postPasswordRegisterUseCase: PostPasswordRegisterUseCase

    @Inject
    lateinit var postPasswordLoginUseCase: PostPasswordLoginUseCase

    private lateinit var binding: FragmentSetPinBinding
    private lateinit var viewModel: PinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetPinBinding.inflate(inflater)

        val factory = PinViewModelFactory(postPasswordRegisterUseCase, postPasswordLoginUseCase)
        viewModel = ViewModelProvider(this, factory)[PinViewModel::class.java]

        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun observe() = with(viewModel) {
        isFailure.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        isRegisterSuccess.observe(viewLifecycleOwner) {
            postPasswordLogin()
        }

        isLoginSuccess.observe(viewLifecycleOwner) {
            token = it
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}