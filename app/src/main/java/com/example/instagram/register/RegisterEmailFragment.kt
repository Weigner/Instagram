package com.example.instagram.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram.R
import com.example.instagram.databinding.FragmentRegisterEmailBinding

class RegisterEmailFragment : Fragment() {

    private var _binding: FragmentRegisterEmailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

}