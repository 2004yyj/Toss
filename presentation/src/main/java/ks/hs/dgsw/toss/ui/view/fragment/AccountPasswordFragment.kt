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
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.domain.entity.request.SendMoney
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentAccountPasswordBinding
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.viewmodel.fragment.AccountPasswordViewModel

@AndroidEntryPoint
class AccountPasswordFragment : Fragment() {

    private lateinit var binding: FragmentAccountPasswordBinding
    private val viewModel: AccountPasswordViewModel by activityViewModels()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountPasswordBinding.inflate(inflater)
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

        val receiverAccountId = requireArguments().getString("receiverAccountId", "")
        val senderAccountId = requireArguments().getString("senderAccountId", "")
        val money = requireArguments().getInt("money", 0)
        val bankCode = requireActivity().intent.getIntExtra("bankCode", 0)
        val bankName = requireArguments().getString("bankName", "")

        viewModel.isSuccess.observe(viewLifecycleOwner, EventObserver {
            val bundle = bundleOf(
                "receiverAccountId" to receiverAccountId,
                "money" to money,
                "bankName" to bankName
            )
            navController.navigate(R.id.action_accountPasswordFragment_to_sendMoneyFinishFragment, bundle)
        })

        viewModel.isFailure.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        binding.toolbarAccountPassword.setNavigationOnClickListener {
            navController.navigateUp()
        }

        binding.btnSubmitAccountPassword.setOnClickListener {
            val accountPW = viewModel.password.value

            if (accountPW != null) {
                val sendMoney = SendMoney(
                    bankCode,
                    receiverAccountId,
                    accountPW,
                    senderAccountId,
                    money
                )
                viewModel.postSendMoney(sendMoney)
            }
        }

        binding.otpPasswordPin.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.otpPasswordPin.windowToken, 0)
            }
        }
    }

}