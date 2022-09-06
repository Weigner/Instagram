package com.example.instagram.register.email

import androidx.annotation.StringRes
import com.example.instagram.BasePresenter
import com.example.instagram.BaseView

interface RegisterEmail {

    interface Presenter : BasePresenter {
        fun create(email: String)
    }

    interface View: BaseView<Presenter> {

        fun showProgress(enabled: Boolean)

        fun displayEmailFailure(@StringRes emailError: Int?)

        fun onEmailFailure(message: String)

        fun gotToRegisterNamePassword(email: String)
    }
}