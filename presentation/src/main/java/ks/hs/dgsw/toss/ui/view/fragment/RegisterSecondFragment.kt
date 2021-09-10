package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.ui.viewmodel.activity.RegisterViewModel
import ks.hs.dgsw.toss.ui.viewmodel.factory.RegisterViewModelFactory

class RegisterSecondFragment : Fragment() {

    private val factory by lazy { RegisterViewModelFactory() }
    private val viewModel: RegisterViewModel by activityViewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun observe() = with(viewModel) {
        id.observe(viewLifecycleOwner) {
            idError.value = if (it.length !in 3..12)
                resources.getString(R.string.please_set_id)
            else ""
        }
    }

}