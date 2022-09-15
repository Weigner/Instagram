package com.weigner.instagram.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.weigner.instagram.R
import com.weigner.instagram.databinding.FragmentRegisterWelcomeBinding
import com.weigner.instagram.util.hideKeyboard
import java.lang.IllegalArgumentException

class RegisterWelcomeFragment : Fragment() {

    private var _binding: FragmentRegisterWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString(KEY_NAME) ?: throw IllegalArgumentException("name not found")

        binding.run {
            tvWelcome.text = getString(R.string.welcome_to_instagram, name)

            btNext.isEnabled = true
            btNext.setOnClickListener {
                goToPhoto()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun goToPhoto() {
        findNavController().navigate(R.id.action_nav_register_welcome_to_nav_register_upload_photo)
    }

    companion object {
        const val KEY_NAME = "key_name"
    }
}