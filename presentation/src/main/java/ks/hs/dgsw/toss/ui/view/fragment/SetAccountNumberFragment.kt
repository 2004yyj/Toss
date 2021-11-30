package ks.hs.dgsw.toss.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ks.hs.dgsw.toss.databinding.FragmentSetAccountNumberBinding
import ks.hs.dgsw.toss.ui.view.activity.SendMoneyActivity
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
        observe()
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

    private fun observe() = with(viewModel) {
        val bankCode = requireArguments().getInt("bankCode")

        accountNumber.observe(viewLifecycleOwner) {
            if (it.length >= 3) {
                val isBankCodeAuthenticated = bankAuth(bankCode, it)
                binding.tilAccountNumberSetAccountNumber.error =
                    "${bankName.value} 은행의 유효한 계좌 번호를 입력해 주십시오."
                binding.tilAccountNumberSetAccountNumber.isErrorEnabled =
                    !isBankCodeAuthenticated
                binding.btnSubmitSetAccountNumber.isEnabled =
                    isBankCodeAuthenticated
            } else {
                binding.btnSubmitSetAccountNumber.isEnabled = false
                binding.tilAccountNumberSetAccountNumber.isErrorEnabled = false
            }
        }
    }

    private fun listener() = with(binding) {
        btnSelectBankSetAccountNumber.setOnClickListener {
            findNavController().navigateUp()
        }

        btnSubmitSetAccountNumber.setOnClickListener {
            val receiverAccountNumber = viewModel.accountNumber.value ?: ""
            val senderAccountNumber = requireActivity().intent.getStringExtra("senderAccountNumber")
            val bankName = requireArguments().getString("bankName")
            val bankCode = requireArguments().getInt("bankCode")

            if (receiverAccountNumber.length == 13) {
                if (bankAuth(bankCode, receiverAccountNumber)) {
                    val intent = Intent(context, SendMoneyActivity::class.java)
                    intent.putExtra("senderAccountNumber", senderAccountNumber)
                    intent.putExtra("receiverAccountNumber", receiverAccountNumber)
                    intent.putExtra("bankName", bankName)
                    intent.putExtra("bankCode", bankCode)
                    startActivity(intent)
                } else {
                    Toast.makeText(context, "$bankName 은행의 유효한 계좌 번호를 입력해 주십시오.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "계좌 양식에 맞게 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init() = with(viewModel) {
        bankName.value = requireArguments().getString("bankName")
    }

    private fun bankAuth(selectedBankCode: Int, accountNumber: String): Boolean {
        val accountNumberBankCode = accountNumber.subSequence(0, 3).toString().toInt()
        return selectedBankCode == accountNumberBankCode
    }

}