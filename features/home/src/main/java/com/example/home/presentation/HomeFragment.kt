package com.example.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding
import com.example.ui.adapters.PostsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.max

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val postsListAdapter: PostsListAdapter by lazy { PostsListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNewPostField()
        setupPosts()
        setupObservers()
    }

    private fun setupNewPostField() {
        binding.homeTextInputEditText.addTextChangedListener {
            handleOnTextChanged(it.toString())
        }
    }

    private fun handleOnTextChanged(newText: String) = with(binding) {
        configNewPostState(newText.isNotEmpty())
        homePostCounter.text = max(MAX_CHARACTERS - newText.length, ZERO_CHARACTERS).toString()
    }

    private fun configNewPostState(enable: Boolean) = with(binding) {
        if (homeButton.isVisible != enable) {
            homeButton.isVisible = enable
            homePostCounter.isVisible = enable
            homePostCounterText.isVisible = enable
        }
    }

    private fun setupPosts() = with(binding.rvPosts) {
        layoutManager = LinearLayoutManager(
            this@HomeFragment.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = postsListAdapter
    }

    private fun setupObservers() = with(viewModel) {
        posts.observe(viewLifecycleOwner) {
            postsListAdapter.submitList(it)
        }
        progressBarVisibility.observe(viewLifecycleOwner) { isLoading ->
            binding.rvPosts.isVisible = !isLoading
            binding.homeProgressBar.isVisible = isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val MAX_CHARACTERS = 777
        private const val ZERO_CHARACTERS = 0
    }
}