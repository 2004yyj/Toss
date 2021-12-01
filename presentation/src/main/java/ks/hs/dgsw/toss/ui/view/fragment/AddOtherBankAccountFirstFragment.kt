package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentAddOtherBankAccountFirstBinding
import ks.hs.dgsw.toss.ui.viewmodel.fragment.AddOtherBankAccountFirstViewModel

class AddOtherBankAccountFirstFragment : Fragment() {

    private lateinit var binding: FragmentAddOtherBankAccountFirstBinding
    private val viewModel: AddOtherBankAccountFirstViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddOtherBankAccountFirstBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        listener()
    }

    private fun listener() {
        binding.btnNextAddOtherBankAccount.setOnClickListener {
            val name = viewModel.name.value ?: ""
            val securityNumberSecond = viewModel.securityNumberSecond.value ?: ""
            val birth =
                if (securityNumberSecond[0] == '1' || securityNumberSecond[0] == '2') {
                    "19${viewModel.securityNumberFirst.value ?: ""}"
                } else {
                    "20${viewModel.securityNumberFirst.value ?: ""}"
                }

            if (name.length >= 2 && birth.length == 8 && securityNumberSecond.length == 7) {
                val bundle = bundleOf(
                    "name" to name,
                    "birth" to birth
                )
                findNavController().navigate(R.id.action_addOtherBankAccountFirstFragment_to_addOtherBankAccountSecondFragment, bundle)
            }
        }

        binding.toolbarAddOtherBankFirstAccount.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

    private fun observe() = with(viewModel) {
        name.observe(viewLifecycleOwner) {
            with(binding.tilAccountNameAddOtherBankAccount) {
                isErrorEnabled = it.isEmpty()
                error = if (it.isEmpty()) resources.getString(R.string.please_set_name) else ""
            }
            binding.btnNextAddOtherBankAccount.isEnabled = it.length >= 2 && securityNumberFirst.value?.length == 6 && securityNumberSecond.value?.length == 7
        }

        securityNumberFirst.observe(viewLifecycleOwner) {
            with(binding.tilSsnFirstAddOtherBankAccount) {
                isErrorEnabled = it.isEmpty()
                error = if (it.isEmpty()) resources.getString(R.string.please_set_security_number) else ""
            }
            binding.btnNextAddOtherBankAccount.isEnabled = it.length == 6 && securityNumberSecond.value?.length == 7 && (name.value?:"").length >= 2
        }

        securityNumberSecond.observe(viewLifecycleOwner) {
            with(binding.tilSsnSecondAddOtherBankAccount) {
                isErrorEnabled = it.isEmpty()
                error = if (it.isEmpty()) resources.getString(R.string.please_set_security_code) else ""
            }
            binding.btnNextAddOtherBankAccount.isEnabled = it.length == 7 && securityNumberFirst.value?.length == 6 && (name.value?:"").length >= 2
        }
    }
}