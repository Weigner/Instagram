package com.weigner.instagram.profile.data

import com.weigner.instagram.RequestCallback
import com.weigner.instagram.model.Post
import com.weigner.instagram.model.UserAuth

class ProfileRepository(private val dataSource: ProfileDataSource) {

    fun fetchUserProfile(userUUID: String,  callback: RequestCallback<UserAuth>) {
        dataSource.fetchUserProfile(userUUID, callback)
    }

    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        dataSource.fetchUserPosts(userUUID, callback)
    }
}