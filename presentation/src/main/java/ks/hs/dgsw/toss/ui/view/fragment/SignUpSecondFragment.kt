package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.ui.viewmodel.activity.SignUpViewModel
import ks.hs.dgsw.toss.ui.viewmodel.factory.SignUpViewModelFactory

class SignUpSecondFragment : Fragment() {

    private val factory by lazy { SignUpViewModelFactory() }
    private val viewModel: SignUpViewModel by activityViewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_second, container, false)
    }

}