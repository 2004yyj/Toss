package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.domain.entity.request.PostAddOtherAccount
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentAddOtherBankAccountSecondBinding
import ks.hs.dgsw.toss.ui.view.adapter.SelectAccountAdapter
import ks.hs.dgsw.toss.ui.viewmodel.fragment.AddOtherBankAccountSecondViewModel

@AndroidEntryPoint
class AddOtherBankAccountSecondFragment : Fragment() {

    private lateinit var adapter: SelectAccountAdapter
    private val viewModel: AddOtherBankAccountSecondViewModel by viewModels()
    private lateinit var binding: FragmentAddOtherBankAccountSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddOtherBankAccountSecondBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
        listener()
    }

    private fun init() {
        val name = requireArguments().getString("name", "")
        val birth = requireArguments().getString("birth", "")

        viewModel.getOtherAccounts(birth, name)

        adapter = SelectAccountAdapter()
        binding.rvSelectAccountAddOtherBankSecondAccount.adapter = adapter
    }

    private fun observe() = with(viewModel) {
        isGetOtherAccountsSuccess.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.tvSelectAccountAddOtherBankSecondAccount.visibility =
                if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
        }

        isPostAddOtherAccount.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_addOtherBankAccountSecondFragment_to_addOtherBankAccountFinishFragment)
        }
    }

    private fun listener() {
        binding.toolbarAddOtherBankSecondAccount.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnNextAddOtherBankAccount.setOnClickListener {
            val postAddOtherAccount = PostAddOtherAccount(adapter.getSelectedList())
            viewModel.postAddOtherAccount(postAddOtherAccount)
        }
    }
}