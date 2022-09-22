package com.weigner.instagram.profile.data

import com.weigner.instagram.RequestCallback
import com.weigner.instagram.model.Post
import com.weigner.instagram.model.UserAuth

interface ProfileDataSource {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>)

    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)
}