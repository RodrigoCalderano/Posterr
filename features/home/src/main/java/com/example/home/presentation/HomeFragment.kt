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
import com.example.models.domain.Post
import com.example.ui.adapters.PostsListAdapter
import com.example.ui.extensions.showRepostBottomSheet
import com.example.ui.models.UiPost
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
        uiState.observe(viewLifecycleOwner) { homeUiState ->
            when (homeUiState) {
                is HomeUiState.Loading -> handleLoadingState(isLoading = true)
                is HomeUiState.NewPosts -> handleNewPosts(homeUiState.posts)
                is HomeUiState.Error -> handleErrorState(homeUiState.message)
                is HomeUiState.Repost -> showRepostBottomSheet(homeUiState.post, ::onReposted)
            }
        }
    }

    private fun handleErrorState(message: String) = with(binding) {
        homeErrorMessage.text = message
        homeProgressBar.isVisible = false
        homeErrorMessage.isVisible = true
    }

    private fun handleLoadingState(isLoading: Boolean) = with(binding) {
        homeErrorMessage.isVisible = false
        rvPosts.isVisible = !isLoading
        homeProgressBar.isVisible = isLoading
    }

    private fun handleNewPosts(posts: List<UiPost>) {
        handleLoadingState(isLoading = false)
        postsListAdapter.submitList(posts)
    }

    private fun onReposted(post: Post, quoteText: String?) =
        viewModel.onRepostConfirmed(post, quoteText)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val MAX_CHARACTERS = 777
        private const val ZERO_CHARACTERS = 0
    }
}