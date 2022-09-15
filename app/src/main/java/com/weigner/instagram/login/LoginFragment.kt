package com.weigner.instagram.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.weigner.instagram.R
import com.weigner.instagram.databinding.FragmentLoginBinding
import com.weigner.instagram.di.DependencyInjector
import com.weigner.instagram.login.data.FakeDataSource
import com.weigner.instagram.login.data.LoginRepository
import com.weigner.instagram.util.TxtWatcher

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
        presenter = LoginPresenter(this, DependencyInjector.loginRepository())
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
            }

            tvRegister.setOnClickListener {
                goToRegister()
            }
        }
    }

    private fun goToHome() {
        findNavController().navigate(R.id.action_nav_login_to_nav_home)

    }

    private fun goToRegister() {
        findNavController().navigate(R.id.action_nav_login_to_nav_register_email)
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
        goToHome()
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}