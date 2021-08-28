package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentHomeBinding
import ks.hs.dgsw.toss.ui.viewmodel.factory.HomeViewModelFactory
import ks.hs.dgsw.toss.ui.viewmodel.fragment.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, HomeViewModelFactory())[HomeViewModel::class.java]
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewModel.getAccounts()
    }
}