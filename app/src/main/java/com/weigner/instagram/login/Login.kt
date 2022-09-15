package com.weigner.instagram.login

import com.weigner.instagram.BasePresenter
import com.weigner.instagram.BaseView

interface Login {

    interface Presenter : com.weigner.instagram.BasePresenter {
        fun login(email: String, password: String)
    }

    interface View : com.weigner.instagram.BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message: String)
    }
}