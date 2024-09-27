package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentSetSenderAccountBinding
import ks.hs.dgsw.toss.ui.view.adapter.AccountAdapter
import ks.hs.dgsw.toss.ui.view.adapter.AccountAdapter.Companion.REMIT_BUTTON_INVISIBLE
import ks.hs.dgsw.toss.ui.viewmodel.fragment.SetSenderAccountViewModel

@AndroidEntryPoint
class SetSenderAccountFragment : Fragment() {

    private val viewModel: SetSenderAccountViewModel by viewModels()
    private lateinit var binding: FragmentSetSenderAccountBinding
    private lateinit var adapter: AccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetSenderAccountBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAccounts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarSetSenderAccount.setNavigationOnClickListener {
            requireActivity().finish()
        }

        adapter = AccountAdapter(REMIT_BUTTON_INVISIBLE) {
            val bundle = bundleOf("senderAccountNumber" to it.account)
            findNavController().navigate(R.id.action_setSenderAccountFragment_to_setMoneyFragment, bundle)
        }

        binding.rvAccountListSetSenderAccount.adapter = adapter

        viewModel.isSuccess.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}