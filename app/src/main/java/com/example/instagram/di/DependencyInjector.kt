package com.example.instagram.di

import com.example.instagram.login.data.FakeDataSource
import com.example.instagram.login.data.LoginRepository
import com.example.instagram.register.email.data.FakeRegisterEmailDataSource
import com.example.instagram.register.email.data.RegisterEmailRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterEmailRepository {
        return RegisterEmailRepository(FakeRegisterEmailDataSource())
    }
}