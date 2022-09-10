package com.example.instagram.register.name_password

import com.example.instagram.R
import com.example.instagram.register.data.RegisterCallback
import com.example.instagram.register.data.RegisterRepository

class RegisterNamePasswordPresenter(
    private var view: RegisterNamePassword.View?,
    private val repository: RegisterRepository
) : RegisterNamePassword.Presenter {

    override fun create(
        email: String,
        name: String,
        password: String,
        passwordConfirmation: String
    ) {
        val isNameValid = name.length > 3
        val isPasswordValid = password.length >= 6
        val isConfirmPassword = password == passwordConfirmation

        if (!isNameValid) {
            view?.displayNameFailure(R.string.invalid_name)
        } else {
            view?.displayNameFailure(null)
        }

        if (!isPasswordValid) {
            view?.displayPasswordFailure(R.string.invalid_password)
        } else if(password != passwordConfirmation) {
            view?.displayPasswordFailure(R.string.password_not_equal)
        } else {
            view?.displayPasswordFailure(null)
        }

        if (isNameValid && isPasswordValid && isConfirmPassword) {
            view?.showProgress(true)

            repository.create(email, name, password, object : RegisterCallback {
                override fun onSuccess() {
                    view?.onCreateSuccess(name)
                }

                override fun onFailure(message: String) {
                    view?.onCreateFailure(message)
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