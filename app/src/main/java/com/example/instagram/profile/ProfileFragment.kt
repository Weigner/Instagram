package com.example.instagram.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.databinding.FragmentProfileBinding
import com.example.instagram.databinding.ItemProfileGridBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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
        binding.rvPhotos.adapter = PostAdapter()
    }

    private class PostAdapter() : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

        private var _binding: ItemProfileGridBinding? = null
        private val binding get() = _binding!!

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            _binding = ItemProfileGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PostViewHolder(binding)
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(R.drawable.ic_insta_add)
        }

        override fun getItemCount(): Int {
            return 30
        }

        private class PostViewHolder(val binding: ItemProfileGridBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(image: Int) {
                binding.ivGrid.setImageResource(image)
            }
        }
    }
}