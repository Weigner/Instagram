package com.example.instagram.register.data

interface RegisterDataSource {
    fun saveEmail(email: String, callback: RegisterCallback)
    fun create(email: String, name: String, password: String, callback: RegisterCallback)
}
