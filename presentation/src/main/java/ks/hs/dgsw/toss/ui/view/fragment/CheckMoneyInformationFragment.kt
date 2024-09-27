package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentCheckMoneyInformationBinding

@AndroidEntryPoint
class CheckMoneyInformationFragment : Fragment() {

    private lateinit var binding: FragmentCheckMoneyInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckMoneyInformationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val senderAccountName = requireArguments().getString("senderAccountName", "")
        val senderAccountId = requireArguments().getString("senderAccountId", "")
        val receiverAccountUserName = requireArguments().getString("receiverAccountUserName", "")
        val receiverBankName = requireArguments().getString("receiverBankName", "")
        val receiverAccountId = requireArguments().getString("receiverAccountId", "")
        val money = requireArguments().getInt("money")

        binding.tvSenderAccountNameCheckMoneyInformation.text = String.format("내 %s 계좌에서", senderAccountName)
        binding.tvSenderAccountNumberCheckMoneyInformation.text = senderAccountId
        binding.tvReceiverNameCheckMoneyInformation.text = String.format("%s 님에게", receiverAccountUserName)
        binding.tvReceiverAccountNumberCheckMoneyInformation.text = "$receiverBankName $receiverAccountId"
        binding.tvMoneyNumberCheckMoneyInformation.text = String.format("%,d원을 보낼게요", money)

        binding.btnSendMoneyCheckMoneyInformation.setOnClickListener {
            val bundle = bundleOf(
                "receiverAccountId" to receiverAccountId,
                "senderAccountId" to senderAccountId,
                "money" to money,
                "bankName" to receiverBankName
            )
            // 0, 1(카뱅), 2(토스)

            findNavController().navigate(R.id.action_checkMoneyInformationFragment_to_accountPasswordFragment, bundle)
        }

        binding.toolbarCheckMoneyInformation.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}