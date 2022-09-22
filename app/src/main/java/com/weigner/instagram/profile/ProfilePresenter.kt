package com.weigner.instagram.profile

import com.weigner.instagram.RequestCallback
import com.weigner.instagram.model.Database
import com.weigner.instagram.model.Post
import com.weigner.instagram.model.UserAuth
import com.weigner.instagram.profile.data.ProfileRepository
import java.lang.RuntimeException

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter{
    override fun fetchUserProfile() {
        view?.showProgress()
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("User not found")
        repository.fetchUserProfile(userUUID, object : RequestCallback<UserAuth> {
            override fun onSuccess(data: UserAuth) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
            }

        })
    }

    override fun fetchUserPosts() {
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("User not found")
        repository.fetchUserPosts(userUUID, object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (data.isEmpty()) {
                    view?.displayEmptyPosts()
                } else {
                    view?.displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}