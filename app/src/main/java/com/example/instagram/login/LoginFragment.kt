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

class LoginFragment : Fragment(), Login.View {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override lateinit var presenter: Login.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = LoginPresenter(this)
        setListener()
    }

    override fun onDestroy() {
        _binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setListener() {
        val textWatcher = TxtWatcher {
            binding.btEnter.isEnabled = binding.etEmail.text.toString().isNotEmpty()
                    && binding.etPassword.text.toString().isNotEmpty()
        }

        binding.apply {
            etEmail.addTextChangedListener(textWatcher)
            etEmail.addTextChangedListener(TxtWatcher {
                displayEmailFailure(null)
            })

            etPassword.addTextChangedListener(textWatcher)
            etPassword.addTextChangedListener(TxtWatcher {
                displayPasswordFailure(null)
            })

            btEnter.setOnClickListener {
                presenter.login(etEmail.text.toString(), etPassword.text.toString())
                //simulando um delay de requisição no backend
                /*Handler(Looper.getMainLooper()).postDelayed({
                    btEnter.showProgress(false)
                }, 5000)*/
            }
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding.btEnter.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.ilEmail.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.ilPassword.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        //TODO("ir para a tela principal")
    }

    override fun onUserUnauthorized() {
        //TODO("Mostrar um alerta")
    }
}