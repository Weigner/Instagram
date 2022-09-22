package com.weigner.instagram.di

import com.weigner.instagram.login.data.FakeDataSource
import com.weigner.instagram.login.data.LoginRepository
import com.weigner.instagram.profile.data.FakeProfileDataSource
import com.weigner.instagram.profile.data.ProfileRepository
import com.weigner.instagram.register.data.FakeRegisterDataSource
import com.weigner.instagram.register.data.RegisterRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FakeRegisterDataSource())
    }

    fun profileRepository(): ProfileRepository {
        return ProfileRepository(FakeProfileDataSource())
    }
}