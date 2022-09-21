package com.weigner.instagram.profile

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weigner.instagram.R
import com.weigner.instagram.databinding.ItemProfileGridBinding
import com.weigner.instagram.model.Post

class PostAdapter() : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var _binding: ItemProfileGridBinding? = null
    private val binding get() = _binding!!

    var items: List<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        _binding = ItemProfileGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position].uri)
    }

    override fun getItemCount(): Int {
        return 30
    }

    class PostViewHolder(val binding: ItemProfileGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Uri) {
            binding.ivGrid.setImageURI(image)
        }
    }
}