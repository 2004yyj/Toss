package ks.hs.dgsw.toss.ui.view.fragment

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentSetMoneyBinding
import ks.hs.dgsw.toss.ui.viewmodel.fragment.SetMoneyViewModel

@AndroidEntryPoint
class SetMoneyFragment : Fragment() {

    private val viewModel: SetMoneyViewModel by viewModels()
    private lateinit var binding: FragmentSetMoneyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentSetMoneyBinding.inflate(inflater)
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

    private fun listener() = with(binding) {
        btnSubmitSetMoney.setOnClickListener {
            val money = viewModel.money.value ?: 0

            if (money != 0) {
                val bundle = bundleOf(
                    "receiverAccountId" to viewModel.receiverAccountId,
                    "receiverAccountUserName" to viewModel.isGetReceiverSuccess.value?.user?.name,
                    "receiverBankName" to (requireActivity().intent.getStringExtra("bankName") ?: ""),
                    "senderAccountId" to viewModel.senderAccountId,
                    "senderAccountName" to viewModel.isGetSenderSuccess.value?.name,
                    "money" to money
                )

                findNavController().navigate(R.id.action_setMoneyFragment_to_checkMoneyInformationFragment, bundle)
            }
        }
    }

    private fun init() {
        viewModel.bankCode = requireActivity().intent.getIntExtra("bankCode", 0)
        viewModel.receiverAccountId = requireActivity().intent.getStringExtra("receiverAccountNumber") ?: ""
        viewModel.senderAccountId = requireActivity().intent.getStringExtra("senderAccountNumber") ?: requireArguments().getString("senderAccountNumber") ?: ""

        viewModel.getMyAccount(viewModel.senderAccountId)
        viewModel.getAccount(viewModel.bankCode, viewModel.receiverAccountId)
        binding.etMoneyNumberSetMoney.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.etMoneyNumberSetMoney.windowToken, 0)
            }
        }
        binding.toolbarSetMoney.setNavigationOnClickListener {
            if (findNavController().previousBackStackEntry == null) {
                requireActivity().finish()
            } else {
                findNavController().navigateUp()
            }
        }
    }

    private fun observe() = with(viewModel) {
        isGetSenderSuccess.observe(viewLifecycleOwner) { senderAccount ->
            binding.tvSenderAccountNameSetMoney.text = String.format("내 %s 계좌에서", senderAccount.name)
            binding.tvSenderAccountNumberSetMoney.text = viewModel.senderAccountId
            isGetReceiverSuccess.observe(viewLifecycleOwner) { receiverAccount ->
                binding.tvReceiverNameSetMoney.text = String.format("%s 님에게", receiverAccount.userId)
                binding.tvReceiverAccountNumberSetMoney.text =
                    "${requireActivity().intent.getStringExtra("bankName")} ${receiverAccount.account}"
            }

            money.observe(viewLifecycleOwner) {
                when {
                    (it + 500) > senderAccount.money -> {
                        binding.etMoneyNumberSetMoney.setText(String.format("%,d원 + 수수료 500원", it))
                        binding.tvKoreanMoneySetMoney.typeface = Typeface.DEFAULT_BOLD
                        binding.tvKoreanMoneySetMoney.setTextColor(resources.getColor(R.color.red, resources.newTheme()))
                        binding.tvKoreanMoneySetMoney.text = String.format("계좌 잔액 %,d원", senderAccount.money)
                        binding.btnSubmitSetMoney.isEnabled = false
                    }
                    else -> {
                        binding.tvKoreanMoneySetMoney.typeface = Typeface.DEFAULT
                        binding.tvKoreanMoneySetMoney.setTextColor(resources.getColor(R.color.text_grey, resources.newTheme()))

                        if (it == 0) {
                            binding.etMoneyNumberSetMoney.setText("")
                            binding.tvKoreanMoneySetMoney.text = ""
                        } else {
                            binding.etMoneyNumberSetMoney.setText(String.format("%,d원 + 수수료 500원", it))
                            binding.tvKoreanMoneySetMoney.text = it.toString().toNumKorString()
                        }
                        binding.btnSubmitSetMoney.isEnabled = true
                    }
                }
            }
        }
    }

    private fun String.toNumKorString(): String {
        val kor1 = listOf("", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        val kor2 = listOf("", "십", "백", "천")
        val kor3 = listOf("", "만", "억", "조", "경")

        val strLen = this.length
        var strRet = ""

        this.forEachIndexed { i, c ->
            val n = (c-48).code

            val digit = strLen - i - 1
            if(n > 0) {
                strRet = "$strRet${kor1[n]}${kor2[digit%4]}"
            }

            if(digit % 4 == 0){
                strRet = "$strRet${kor3[digit/4]}"
            }
        }

        return strRet + "원"
    }
}