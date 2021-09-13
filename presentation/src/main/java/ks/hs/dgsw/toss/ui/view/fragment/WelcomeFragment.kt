package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentWelcomeBinding
import ks.hs.dgsw.toss.ui.viewmodel.activity.RegisterViewModel

@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private val navController: NavController by lazy { findNavController() }
    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        (requireActivity() as AppCompatActivity).viewModelStore.clear()

        btnLogin = binding.btnLoginWelcome
        btnSignUp = binding.btnSignUpWelcome

        btnLogin.setOnClickListener { navController.navigate(R.id.action_welcomeFragment_to_loginFragment) }
        btnSignUp.setOnClickListener { navController.navigate(R.id.action_welcomeFragment_to_signUpFragment) }
    }

}