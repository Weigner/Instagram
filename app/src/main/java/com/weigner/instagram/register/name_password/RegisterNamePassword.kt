package com.weigner.instagram.register.name_password

import androidx.annotation.StringRes
import com.weigner.instagram.BasePresenter
import com.weigner.instagram.BaseView

interface RegisterNamePassword {

    interface Presenter : com.weigner.instagram.BasePresenter {
        fun create(email: String, name: String, password : String, passwordConfirmation: String)
    }

    interface View: com.weigner.instagram.BaseView<Presenter> {

        fun showProgress(enabled: Boolean)

        fun displayNameFailure(@StringRes nameError: Int?)

        fun displayPasswordFailure(@StringRes passwordError: Int?)

        fun onCreateFailure(message: String)

        fun onCreateSuccess(name: String)

    }
}