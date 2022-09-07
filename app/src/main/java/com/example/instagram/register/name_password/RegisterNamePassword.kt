package com.example.instagram.register.name_password

import androidx.annotation.StringRes
import com.example.instagram.BasePresenter
import com.example.instagram.BaseView

interface RegisterNamePassword {

    interface Presenter : BasePresenter {
        fun create(email: String, name: String, password : String, passwordConfirmation: String)
    }

    interface View: BaseView<Presenter> {

        fun showProgress(enabled: Boolean)

        fun displayNameFailure(@StringRes nameError: Int?)

        fun displayPasswordFailure(@StringRes passwordError: Int?)

        fun onCreateFailure(message: String)

        fun onCreateSuccess(name: String)

    }
}