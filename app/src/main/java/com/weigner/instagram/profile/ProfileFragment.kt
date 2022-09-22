package com.weigner.instagram.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.weigner.instagram.R
import com.weigner.instagram.databinding.FragmentProfileBinding
import com.weigner.instagram.di.DependencyInjector
import com.weigner.instagram.model.Post
import com.weigner.instagram.model.UserAuth

class ProfileFragment : Fragment(), Profile.View {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override lateinit var presenter: Profile.Presenter

    private val adapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPhotos.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvPhotos.adapter = adapter

        val repository = DependencyInjector.profileRepository()
        presenter = ProfilePresenter(this, repository)

        presenter.fetchUserProfile()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun showProgress(enabled: Boolean) {
        binding.pb.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayUserProfile(userAuth: UserAuth) {
        binding.apply {
            tvPostsCount.text = userAuth.postCount.toString()
            tvFollowingCount.text = userAuth.followingCount.toString()
            tvFollowersCount.text = userAuth.followersCount.toString()
            tvUserName.text = userAuth.name
            tvBio.text = "TODO"// TODO userAuth.bio
            presenter.fetchUserPosts()
        }
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyPosts() {
        binding.tvEmpty.visibility = View.VISIBLE
        binding.rvPhotos.visibility = View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun displayFullPosts(posts: List<Post>) {
        binding.tvEmpty.visibility = View.GONE
        binding.rvPhotos.visibility = View.VISIBLE
        // TODO Atualizar adapter
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }
}