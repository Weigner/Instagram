package com.example.instagram.register.email.data

import android.os.Handler
import android.os.Looper
import com.example.instagram.model.Database


class FakeRegisterEmailDataSource : RegisterEmailDataSource {

    override fun saveEmail(email: String, callback: RegisterEmailCallback) {
        //simulando um delay de requisição no backend
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull{ email == it.email }

            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("Usuário já cadastrado")
            }
            callback.onComplete()
        }, 5000)
    }

}