package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentSendMoneyFinishBinding

class SendMoneyFinishFragment : Fragment() {

    private lateinit var binding: FragmentSendMoneyFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSendMoneyFinishBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receiverAccountId = requireArguments().getString("receiverAccountId", "")
        val money = requireArguments().getInt("money", 0)
        val bankName = requireArguments().getString("bankName", "")

        binding.tvReceivedAccountSendMoneyFinish.text = "$bankName $receiverAccountId"
        binding.tvReceivedMoneySendMoneyFinish.text = String.format("%,d원", money)

        binding.btnSendMoneyFinish.setOnClickListener {
            requireActivity().finish()
        }
    }
}