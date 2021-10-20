package ks.hs.dgsw.toss.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentAddAccountSecondBinding
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.viewmodel.fragment.AddAccountViewModel

@AndroidEntryPoint
class AddAccountSecondFragment : Fragment() {

    private lateinit var binding: FragmentAddAccountSecondBinding
    private val viewModel: AddAccountViewModel by activityViewModels()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddAccountSecondBinding.inflate(inflater)
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

        viewModel.isSuccessAddAccount.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(R.id.action_addAccountSecondFragment_to_addAccountSuccessFragment)
        })

        viewModel.isFailure.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        binding.toolbarAddAccountFirst.setNavigationOnClickListener {
            navController.navigateUp()
        }

        binding.otpPasswordPin.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.otpPasswordPin.windowToken, 0)
            }
        }
    }

}