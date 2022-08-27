package com.example.instagram.login.data

import android.os.Handler
import android.os.Looper


class FakeDataSource : LoginDataSource {

    override fun login(email: String, password: String, callback: LoginCallback) {
        //simulando um delay de requisição no backend
        Handler(Looper.getMainLooper()).postDelayed({
            if (email == "erickweiner@gmail.com" && password == "123456") {
                callback.onSuccess()
            } else {
                callback.onFailure("Usuário não encontrado")
            }
            callback.onComplete()
        }, 5000)
    }

}