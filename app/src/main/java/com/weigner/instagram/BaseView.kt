package com.weigner.instagram

import com.weigner.instagram.login.Login

interface BaseView<T> {
    var presenter: T
}