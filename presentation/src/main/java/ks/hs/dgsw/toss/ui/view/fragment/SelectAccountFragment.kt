package ks.hs.dgsw.toss.ui.view.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentSelectAccountBinding
import ks.hs.dgsw.toss.ui.view.adapter.BankAdapter
import ks.hs.dgsw.toss.ui.view.decoration.GridLayoutSpacingDecoration
import ks.hs.dgsw.toss.ui.viewmodel.factory.SelectAccountViewModelFactory
import ks.hs.dgsw.toss.ui.viewmodel.fragment.SelectAccountViewModel

class SelectAccountFragment : Fragment() {

    private lateinit var binding: FragmentSelectAccountBinding
    private val viewModel: SelectAccountViewModel by activityViewModels()
    private lateinit var bankAdapter: BankAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectAccountBinding.inflate(inflater)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initRecyclerView()
        observe()
    }

    override fun onResume() {
        super.onResume()
        backPressedListener()
    }

    private fun backPressedListener() {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
                requireActivity().overridePendingTransition(R.anim.pop_slide_in_left, R.anim.pop_slide_out_right)
            }
        })
    }

    private fun observe() {
        viewModel.bankList.observe(viewLifecycleOwner) {
            bankAdapter.submitList(it)
        }
    }

    private fun initRecyclerView() = with(binding) {
        bankAdapter = BankAdapter { bank ->
            val bundle = Bundle()
            bundle.putString("bankName", bank.name)
            bundle.putInt("bankCode", bank.code)
            findNavController().navigate(R.id.action_selectAccountFragment_to_setAccountNumberFragment, bundle)
        }
        rvBankListSelectAccount.adapter = bankAdapter
        rvBankListSelectAccount.addItemDecoration(GridLayoutSpacingDecoration(requireActivity(), 3))
    }

    private fun init() = with(viewModel) {
        getBanks()
    }

    companion object {
        fun newInstance() = SelectAccountFragment()
    }
}