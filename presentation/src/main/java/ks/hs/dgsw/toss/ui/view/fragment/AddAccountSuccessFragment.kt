package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentAddAccountSuccessBinding

class AddAccountSuccessFragment : Fragment() {

    private lateinit var binding: FragmentAddAccountSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddAccountSuccessBinding.inflate(inflater)
        binding.type = requireArguments().getString("type", "")
        binding.limit = requireArguments().getInt("limit", 0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (findNavController().currentDestination?.arguments?.isNotEmpty() == true) {
                    finishActivity()
                }
            }
        })

        binding.btnSetPinFinish.setOnClickListener {
            finishActivity()
        }
    }

    private fun finishActivity() {
        requireActivity().finish()
        requireActivity().overridePendingTransition(R.anim.pop_slide_in_left, R.anim.pop_slide_out_right)
    }

}