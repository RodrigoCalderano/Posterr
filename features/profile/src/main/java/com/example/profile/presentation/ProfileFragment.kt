package com.example.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.models.domain.Post
import com.example.models.domain.User
import com.example.profile.R
import com.example.profile.databinding.FragmentProfileBinding
import com.example.ui.adapters.PostsListAdapter
import com.example.ui.extensions.closeKeyBoard
import com.example.ui.extensions.showRepostBottomSheet
import com.example.ui.models.UiPost
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.max

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModel()

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    private val postsListAdapter: PostsListAdapter by lazy { PostsListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNewPostField()
        setupPosts()
        setupPostButton()
        setupObservers()
    }

    private fun setupNewPostField() {
        binding.profileTextInputEditText.addTextChangedListener {
            handleOnTextChanged(it.toString())
        }
    }

    private fun handleOnTextChanged(newText: String) = with(binding) {
        configNewPostState(newText.isNotEmpty())
        profilePostCounter.text = max(MAX_CHARACTERS - newText.length, ZERO).toString()
    }

    private fun configNewPostState(enable: Boolean) = with(binding) {
        if (profileButton.isVisible != enable) {
            profileButton.isVisible = enable
            profilePostCounter.isVisible = enable
            profilePostCounterText.isVisible = enable
        }
    }

    private fun setupPosts() = with(binding.rvPosts) {
        layoutManager = LinearLayoutManager(
            this@ProfileFragment.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = postsListAdapter
    }

    private fun setupPostButton() = with(binding) {
        binding.profileButton.setOnClickListener {
            viewModel.onPostButtonClicked(binding.profileTextInputEditText.text.toString())
            profileTextInputEditText.setText(EMPTY_TEXT)
            closeKeyBoard()
        }
    }

    private fun setupObservers() = with(viewModel) {
        uiState.observe(viewLifecycleOwner) { profileUiState ->
            when (profileUiState) {
                is ProfileUiState.Loading -> handleLoadingState(isLoading = true)
                is ProfileUiState.UpdateScreenSuccessState -> handleSuccessState(
                    profileUiState.posts,
                    profileUiState.user
                )
                is ProfileUiState.Error -> handleErrorState(profileUiState.message)
                is ProfileUiState.Repost -> showRepostBottomSheet(profileUiState.post, ::onReposted)
                is ProfileUiState.Toast -> handleToastState(profileUiState.message)
            }
        }
    }

    private fun handleLoadingState(isLoading: Boolean) = with(binding) {
        profileErrorMessage.isVisible = false
        rvPosts.isVisible = !isLoading
        profileProgressBar.isVisible = isLoading
    }

    private fun handleSuccessState(posts: List<UiPost>, user: User) = with(binding) {
        profileDataJoined.text = user.profileDataJoined
        profileOriginalPosts.text = user.profileOriginalPosts.toString()
        profileReposts.text = user.profileReposts.toString()
        profileQuotePosts.text = user.profileQuotePosts.toString()
        profileName.text = user.userName
        handleLoadingState(isLoading = false)
        postsListAdapter.submitList(posts)
    }

    private fun handleErrorState(message: String) = with(binding) {
        profileErrorMessage.text = message
        profileProgressBar.isVisible = false
        profileErrorMessage.isVisible = true
    }

    private fun handleToastState(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

    private fun onReposted(post: Post, quoteText: String) =
        viewModel.onRepostConfirmed(post, quoteText)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MAX_CHARACTERS = 777
        private const val ZERO = 0
        private const val EMPTY_TEXT = ""
    }
}