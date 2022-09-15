package com.weigner.instagram.register.data

class RegisterRepository(private val data: RegisterDataSource) {
    fun saveEmail(email: String, callback: RegisterCallback) {
        data.saveEmail(email, callback)
    }

    fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        data.create(email, name, password, callback)
    }
}