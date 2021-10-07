package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentHowToLoginBinding

class HowToLoginFragment : Fragment() {

    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentHowToLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHowToLoginBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        btnIdPwLoginHowToLogin.setOnClickListener {
            navController.navigate(R.id.action_howToLoginFragment_to_loginFragment)
        }

        btnPinLoginHowToLogin.setOnClickListener {
            navController.navigate(R.id.action_howToLoginFragment_to_pinFragment)
        }
    }

}