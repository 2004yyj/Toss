package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentSelectSuggestBinding
import ks.hs.dgsw.toss.ui.view.adapter.AccountAdapter
import ks.hs.dgsw.toss.ui.view.adapter.UserAccountAdapter
import ks.hs.dgsw.toss.ui.view.decoration.GridLayoutSpacingDecoration
import ks.hs.dgsw.toss.ui.viewmodel.factory.SelectSuggestViewModelFactory
import ks.hs.dgsw.toss.ui.viewmodel.fragment.SelectSuggestViewModel

class SelectSuggestFragment : Fragment() {

    private val factory by lazy { SelectSuggestViewModelFactory() }
    private val viewModel by viewModels<SelectSuggestViewModel> { factory }
    private lateinit var binding: FragmentSelectSuggestBinding
    private lateinit var userAccountAdapter: UserAccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectSuggestBinding.inflate(inflater)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initRecyclerView()
        observe()
    }

    private fun initRecyclerView() = with(binding) {
        userAccountAdapter = UserAccountAdapter()
        rvAccountUserListSelectSuggest.adapter = userAccountAdapter
    }

    private fun observe() {
        viewModel.accountList.observe(viewLifecycleOwner) {

        }
    }

    private fun init() {
        viewModel.getAccounts()
    }

    companion object {
        fun newInstance() = SelectSuggestFragment()
    }
}