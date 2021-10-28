package ks.hs.dgsw.toss.ui.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentSetAccountNumberBinding
import ks.hs.dgsw.toss.ui.viewmodel.fragment.SetAccountViewModel

class SetAccountNumberFragment : Fragment() {

    private lateinit var binding: FragmentSetAccountNumberBinding
    private val viewModel: SetAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetAccountNumberBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        listener()
    }

    override fun onResume() {
        super.onResume()
        backPressedListener()
    }

    private fun backPressedListener() {
        requireActivity().onBackPressedDispatcher.addCallback(object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        })
    }

    private fun listener() = with(binding) {
        btnSelectBankSetAccountNumber.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun init() = with(viewModel) {
        bankName.value = requireArguments().getString("bankName")
    }

}