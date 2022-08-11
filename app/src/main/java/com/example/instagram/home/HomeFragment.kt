package com.example.instagram.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.databinding.FragmentHomeBinding
import com.example.instagram.databinding.FragmentProfileBinding
import com.example.instagram.databinding.ItemPostBinding
import com.example.instagram.databinding.ItemProfileGridBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHome.adapter = PostAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private class PostAdapter() : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

        private var _binding: ItemPostBinding? = null
        private val binding get() = _binding!!

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            _binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PostViewHolder(binding)
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(R.drawable.ic_insta_add)
        }

        override fun getItemCount(): Int {
            return 30
        }

        private class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(image: Int) {
                binding.ivPost.setImageResource(image)
            }
        }
    }
}