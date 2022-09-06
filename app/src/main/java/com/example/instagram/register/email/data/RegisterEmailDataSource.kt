package com.example.instagram.register.email.data

interface RegisterEmailDataSource {
    fun saveEmail(email: String, callback: RegisterEmailCallback)
}
