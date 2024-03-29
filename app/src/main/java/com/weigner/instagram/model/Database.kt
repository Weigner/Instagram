package com.weigner.instagram.model

import java.util.*

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val posts = hashMapOf<String, Set<Post>>()

    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "Erick", "erickweiner@gmail.com", "123456"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "Teste", "teste@gmail.com", "123456"))

        sessionAuth = usersAuth.first()
    }
}