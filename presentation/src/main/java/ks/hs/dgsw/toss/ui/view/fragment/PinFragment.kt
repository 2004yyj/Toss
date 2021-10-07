package ks.hs.dgsw.toss.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.databinding.FragmentPinBinding
import ks.hs.dgsw.toss.ui.view.activity.MainActivity
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.loginToken
import ks.hs.dgsw.toss.ui.view.util.PreferenceHelper.passwordLoginId
import ks.hs.dgsw.toss.ui.viewmodel.fragment.PinViewModel

@AndroidEntryPoint
class PinFragment : Fragment() {

    private lateinit var binding: FragmentPinBinding
    private val viewModel: PinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPinBinding.inflate(inflater)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoginSuccess.observe(viewLifecycleOwner) {

            loginToken = it.token

            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        viewModel.isFailure.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

}