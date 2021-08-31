package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentSelectAccountBinding
import ks.hs.dgsw.toss.ui.view.decoration.GridLayoutSpacingDecoration
import ks.hs.dgsw.toss.ui.viewmodel.factory.SelectAccountViewModelFactory
import ks.hs.dgsw.toss.ui.viewmodel.fragment.SelectAccountViewModel

class SelectAccountFragment : Fragment() {

    private lateinit var binding: FragmentSelectAccountBinding
    private val factory by lazy { SelectAccountViewModelFactory() }
    private val viewModel by viewModels<SelectAccountViewModel> { factory }

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
    }

    private fun init() {
        viewModel.getBanks()
    }

    companion object {
        fun newInstance() = SelectAccountFragment()
    }
}