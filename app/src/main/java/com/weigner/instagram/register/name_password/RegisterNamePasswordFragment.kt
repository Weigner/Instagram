package com.weigner.instagram.register.name_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.weigner.instagram.R
import com.weigner.instagram.databinding.FragmentRegisterNamePasswordBinding
import com.weigner.instagram.di.DependencyInjector
import com.weigner.instagram.register.RegisterWelcomeFragment.Companion.KEY_NAME
import com.weigner.instagram.util.TxtWatcher
import com.weigner.instagram.util.hideKeyboard
import java.lang.IllegalArgumentException

class RegisterNamePasswordFragment : Fragment(), RegisterNamePassword.View {
    private var _binding: FragmentRegisterNamePasswordBinding? = null
    private val binding get() = _binding!!

    override lateinit var presenter: RegisterNamePassword.Presenter

    private val watcher = TxtWatcher {
        binding.run {
            btContinue.isEnabled = etName.text.toString().isNotEmpty()
                    && etPassword.text.toString().isNotEmpty()
                    && etConfirmationPassword.text.toString().isNotEmpty()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterNamePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = RegisterNamePasswordPresenter(this, DependencyInjector.registerEmailRepository())

        val email = arguments?.getString(KEY_EMAIL) ?: throw IllegalArgumentException("email not found")

        binding.run {
            etName.addTextChangedListener(watcher)
            etPassword.addTextChangedListener(watcher)
            etConfirmationPassword.addTextChangedListener(watcher)

            etName.addTextChangedListener(TxtWatcher { displayNameFailure(null) })

            etPassword.addTextChangedListener(TxtWatcher { displayPasswordFailure(null) })

            etConfirmationPassword.addTextChangedListener(TxtWatcher { displayPasswordFailure(null) })

            btContinue.setOnClickListener {
                hideKeyboard(view)
                presenter.create(email,
                etName.text.toString(), etPassword.text.toString(),
                etConfirmationPassword.text.toString())
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun goToWelcome(name: String) {
        val args = Bundle()
        args.putString(KEY_NAME, name)
        findNavController().navigate(R.id.action_nav_register_name_password_to_nav_register_welcome, args)
    }

    companion object {
        const val KEY_EMAIL = "key_email"
    }

    override fun showProgress(enabled: Boolean) {
        binding.btContinue.showProgress(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
        binding.ilName.error = nameError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.ilPassword.error = passwordError?.let { getString(it) }
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateSuccess(name: String) {
        goToWelcome(name)
    }
}