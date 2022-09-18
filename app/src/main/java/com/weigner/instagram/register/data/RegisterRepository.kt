package com.weigner.instagram.register.data

import android.net.Uri

class RegisterRepository(private val data: RegisterDataSource) {
    fun saveEmail(email: String, callback: RegisterCallback) {
        data.saveEmail(email, callback)
    }

    fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        data.create(email, name, password, callback)
    }

    fun updateUser(photoUri: Uri, callback: RegisterCallback) {
        data.updateUser(photoUri, callback)
    }
}