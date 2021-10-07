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
import ks.hs.dgsw.domain.usecase.user.PostLoginUseCase
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentRegisterFinishBinding
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.loginToken
import ks.hs.dgsw.toss.ui.viewmodel.fragment.LoginViewModel
import ks.hs.dgsw.toss.ui.viewmodel.factory.LoginViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFinishFragment : Fragment() {

    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentRegisterFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterFinishBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        btnSetPinFinish.setOnClickListener {
            navController.navigate(R.id.action_registerFinishFragment_to_setPinFragment, requireArguments())
        }
    }
}