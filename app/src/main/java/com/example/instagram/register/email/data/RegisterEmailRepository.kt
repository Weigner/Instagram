package com.example.instagram.register.email.data

class RegisterEmailRepository(private val data: RegisterEmailDataSource) {
    fun saveEmail(email: String, callback: RegisterEmailCallback) {
        data.saveEmail(email, callback)
    }
}