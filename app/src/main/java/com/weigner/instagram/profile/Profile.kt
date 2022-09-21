package com.weigner.instagram.profile

import com.weigner.instagram.BasePresenter
import com.weigner.instagram.BaseView
import com.weigner.instagram.model.Post
import com.weigner.instagram.model.UserAuth

interface Profile {

    interface Presenter : BasePresenter {
        fun fetchUserProfile()
        fun fetchUserPosts()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean = true)
        fun displayUserProfile(userAuth: UserAuth)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }

}