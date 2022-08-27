package com.example.instagram.login.data

class LoginRepository(private val data: LoginDataSource) {
    fun login(email: String, password: String, callback: LoginCallback) {
        data.login(email, password, callback)
    }
}