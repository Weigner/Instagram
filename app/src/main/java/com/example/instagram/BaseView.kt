package com.example.instagram

import com.example.instagram.login.Login

interface BaseView<T> {
    var presenter: T
}