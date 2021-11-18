package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentUploadProfileImageBinding
import ks.hs.dgsw.toss.ui.view.util.EventObserver
import ks.hs.dgsw.toss.ui.view.util.asMultipart
import ks.hs.dgsw.toss.ui.viewmodel.fragment.UploadProfileImageViewModel

@AndroidEntryPoint
class UploadProfileImageFragment : Fragment() {

    private val navController: NavController by lazy { findNavController() }
    private lateinit var launcher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentUploadProfileImageBinding
    private val viewModel: UploadProfileImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadProfileImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.images != null) {
            binding.ivProfileImageUpload.load(viewModel.images)
        }

        launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null) {
                with(requireActivity()) {
                    viewModel.images = it
                    binding.ivProfileImageUpload.load(it)
                }
            }
        }
        observe()
        listener()
    }

    private fun observe() = with(viewModel) {
        isFailure.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        isSuccess.observe(viewLifecycleOwner, EventObserver {
            if (it.size == 1) {
                val bundle = bundleOf("profileImage" to it[0])
                navController.navigate(R.id.action_uploadProfileImageFragment_to_registerFirstFragment, bundle)
            }
        })
    }

    private fun listener() {
        binding.btnNextUpload.setOnClickListener {
            if (viewModel.images != null) {
                with(requireActivity()) {
                    viewModel.postUploadImage(
                        viewModel.images!!.asMultipart(
                            "images",
                            cacheDir,
                            contentResolver
                        )!!
                    )
                }
            } else {
                Toast.makeText(context, "프로필 이미지를 추가하지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.fabAddImageUpload.setOnClickListener {
            launcher.launch("image/*")
        }
    }

}