package com.weigner.instagram.login.data

import com.weigner.instagram.model.UserAuth

interface LoginCallback {
    fun onSuccess(userAuth: UserAuth)
    fun onFailure(message: String)
    fun onComplete()
}
