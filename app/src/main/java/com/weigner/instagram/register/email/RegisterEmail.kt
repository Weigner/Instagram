package com.weigner.instagram.register.email

import androidx.annotation.StringRes
import com.weigner.instagram.BasePresenter
import com.weigner.instagram.BaseView

interface RegisterEmail {

    interface Presenter : com.weigner.instagram.BasePresenter {
        fun create(email: String)
    }

    interface View: com.weigner.instagram.BaseView<Presenter> {

        fun showProgress(enabled: Boolean)

        fun displayEmailFailure(@StringRes emailError: Int?)

        fun onEmailFailure(message: String)

        fun gotToRegisterNamePassword(email: String)
    }
}