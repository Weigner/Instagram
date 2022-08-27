package com.example.instagram.login

import com.example.instagram.BasePresenter
import com.example.instagram.BaseView

interface Login {

    interface Presenter : BasePresenter{
        fun login(email: String, password: String)
    }

    interface View : BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message: String)
    }
}