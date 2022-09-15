package com.weigner.instagram.login.data

import android.os.Handler
import android.os.Looper
import com.weigner.instagram.model.Database


class FakeDataSource : LoginDataSource {

    override fun login(email: String, password: String, callback: LoginCallback) {
        //simulando um delay de requisição no backend
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull{ email == it.email }

            if (userAuth == null) {
                callback.onFailure("Usuario não encontrado")
            } else if (userAuth.password != password) {
                callback.onFailure("Senha incorreta!")
            } else {
                Database.sessionAuth = userAuth
                callback.onSuccess(userAuth)
            }
            callback.onComplete()
        }, 5000)
    }

}