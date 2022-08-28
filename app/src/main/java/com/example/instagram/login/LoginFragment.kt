package com.example.instagram.login

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
import com.example.instagram.R
import com.example.instagram.databinding.FragmentLoginBinding
import com.example.instagram.di.DependencyInjector
import com.example.instagram.login.data.FakeDataSource
import com.example.instagram.login.data.LoginRepository
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
        }
    }

    private fun goHome() {
        findNavController().navigate(R.id.action_nav_login_to_nav_home)

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
        goHome()
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}