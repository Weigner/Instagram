package com.example.instagram.register.email

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.instagram.R
import com.example.instagram.databinding.FragmentRegisterEmailBinding
import com.example.instagram.di.DependencyInjector
import com.example.instagram.register.name_password.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import com.example.instagram.util.TxtWatcher
import com.example.instagram.util.hideKeyboard

class RegisterEmailFragment : Fragment(), RegisterEmail.View {

    private var _binding: FragmentRegisterEmailBinding? = null
    private val binding get() = _binding!!

    override lateinit var presenter: RegisterEmail.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = RegisterEmailPresenter(this, DependencyInjector.registerEmailRepository())

        binding.tvLogin.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.etEmail.addTextChangedListener(TxtWatcher {
            binding.btNext.isEnabled = binding.etEmail.text.toString().isNotEmpty()
        })

        binding.btNext.setOnClickListener {
            hideKeyboard(view)
            presenter.create(binding.etEmail.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        presenter.onDestroy()
    }

    override fun showProgress(enabled: Boolean) {
        binding.btNext.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.ilEmail.error = emailError?.let { getString(it) }
    }

    override fun onEmailFailure(message: String) {
        binding.ilEmail.error = message
    }

    override fun gotToRegisterNamePassword(email: String) {
        val args = Bundle()
        args.putString(KEY_EMAIL, email)
        findNavController().navigate(R.id.action_nav_register_email_to_nav_register_name_password, args)
    }

}