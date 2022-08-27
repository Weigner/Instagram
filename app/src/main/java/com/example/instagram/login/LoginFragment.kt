package com.example.instagram.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram.databinding.FragmentLoginBinding
import com.example.instagram.util.TxtWatcher

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
        setListener()
    }

    private fun setListener() {

        val textWatcher = TxtWatcher {
            binding.btEnter.isEnabled = it.isNotEmpty()
        }

        binding.apply {
            etEmail.addTextChangedListener(textWatcher)
            etPassword.addTextChangedListener(textWatcher)
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