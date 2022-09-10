package com.example.instagram.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.instagram.R
import com.example.instagram.util.CustomDialog
import com.example.instagram.databinding.FragmentRegisterUploadPhotoBinding
import com.example.instagram.util.hideKeyboard

class RegisterUploadPhotoFragment : Fragment() {

    private var _binding: FragmentRegisterUploadPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterUploadPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btNext.isEnabled = true
        binding.btNext.setOnClickListener {
            val customDialog = CustomDialog(requireContext())
            customDialog.addButton(R.string.add_photo, R.string.gallery) {
                when(it.id) {
                    R.string.add_photo -> {

                    }
                    R.string.gallery -> {

                    }
                }
            }
            customDialog.show()
        }

        binding.btJump.setOnClickListener {
            goToHome()
        }
    }

    private fun goToHome() {
        findNavController().navigate(R.id.action_nav_register_upload_photo_to_nav_home)
        //findNavController().navigateUp()
    }
}