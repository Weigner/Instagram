package com.example.instagram.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btEnter.isEnabled = s.toString().isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }

        binding.apply {
            etEmail.addTextChangedListener(watcher)
            btEnter.setOnClickListener {
                btEnter.showProgress()

                //simulando um delay de requisição no backend
                Handler(Looper.getMainLooper()).postDelayed({
                    btEnter.showProgress(false)
                    ilEmail.error = "erro"
                    ilPassword.error = "erro"
                }, 5000)
            }
        }
    }
}