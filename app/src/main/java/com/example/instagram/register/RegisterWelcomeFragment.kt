package com.example.instagram.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram.databinding.FragmentRegisterWelcomeBinding

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
}