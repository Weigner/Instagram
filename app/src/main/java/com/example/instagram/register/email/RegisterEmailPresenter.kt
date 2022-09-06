package com.example.instagram.register.email

import android.util.Patterns
import com.example.instagram.R
import com.example.instagram.login.data.LoginCallback
import com.example.instagram.model.UserAuth
import com.example.instagram.register.email.data.RegisterEmailCallback
import com.example.instagram.register.email.data.RegisterEmailRepository

class RegisterEmailPresenter(
    private var view: RegisterEmail.View?,
    private val repository: RegisterEmailRepository
) : RegisterEmail.Presenter {
    override fun create(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if (isEmailValid) {
            view?.showProgress(true)

            repository.saveEmail(email, object : RegisterEmailCallback {
                override fun onSuccess() {
                    view?.gotToRegisterNamePassword(email)
                }

                override fun onFailure(message: String) {
                    view?.onEmailFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }

            })
        }
    }

    override fun onDestroy() {
        view = null
    }
}