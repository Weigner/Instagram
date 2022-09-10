package com.example.instagram.register.data

import android.os.Handler
import android.os.Looper
import com.example.instagram.model.Database
import com.example.instagram.model.UserAuth
import java.util.*


class FakeRegisterDataSource : RegisterDataSource {

    override fun saveEmail(email: String, callback: RegisterCallback) {
        //simulando um delay de requisição no backend
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull{ email == it.email }

            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("Usuário já cadastrado")
            }
            callback.onComplete()
        }, 2000)
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull{ email == it.email }

            if (userAuth != null) {
                callback.onFailure("Usuário já cadastrado")
            } else {
                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password)

                val created = Database.usersAuth.add(newUser)

                if (created) {
                    Database.sessionAuth = newUser
                    callback.onSuccess()
                } else {
                    callback.onFailure("Erro para cadastrar usuario")
                }
            }
            callback.onComplete()
        }, 2000)
    }

}