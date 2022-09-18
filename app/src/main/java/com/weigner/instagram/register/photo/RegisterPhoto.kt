package com.weigner.instagram.register.photo

import android.net.Uri
import com.weigner.instagram.BasePresenter
import com.weigner.instagram.BaseView

interface RegisterPhoto {

    interface Presenter : BasePresenter {
        fun updateUser(photoUri: Uri)
    }

    interface View: BaseView<Presenter> {

        fun showProgress(enabled: Boolean)

        fun onUpdateFailure(message: String)

        fun onUpdateSuccess()

    }
}