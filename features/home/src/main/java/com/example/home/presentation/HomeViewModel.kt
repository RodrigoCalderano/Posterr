package com.example.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.AddPostUseCase
import com.example.home.domain.GetFeedUseCase
import com.example.home.presentation.HomeFragment.Companion.MAX_CHARACTERS
import com.example.models.domain.Post
import com.example.models.domain.Post.Companion.PostType.ORIGINAL_POST
import com.example.models.domain.Post.Companion.PostType.QUOTE_POST
import com.example.models.domain.Post.Companion.PostType.REPOST
import com.example.ui.mappers.toUiPost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class HomeViewModel(
    getFeedUseCase: GetFeedUseCase,
    private val addPostUseCase: AddPostUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableLiveData<HomeUiState>(HomeUiState.Loading)
    val uiState: LiveData<HomeUiState> = _uiState

    init {
        getFeedUseCase()
            .flowOn(dispatcher)
            .onStart { _uiState.value = HomeUiState.Loading }
            .onEach { posts -> onNewPostsReceived(posts) }
            .catch { _uiState.value = HomeUiState.Error(GENERIC_ERROR) }
            .launchIn(viewModelScope)
    }

    fun onRepostConfirmed(post: Post, quoteText: String) {
        viewModelScope.launch(context = dispatcher) {
            addPostUseCase(
                post.copy(
                    additionalQuoteText = quoteText,
                    type = if (quoteText.isEmpty()) REPOST else QUOTE_POST,
                )
            )
        }
    }

    fun onPostButtonClicked(postText: String) {
        if (postText.length > MAX_CHARACTERS)
            _uiState.value = HomeUiState.Toast(POST_LENGTH_EXCEEDED)
        else addPost(postText)
    }

    private fun addPost(postText: String) = viewModelScope.launch(context = dispatcher) {
        addPostUseCase(
            Post(
                originalPostText = postText,
                originalPostAuthor = EMPTY,
                type = ORIGINAL_POST,
                userNameAuthor = EMPTY
            )
        )
    }

    private fun onNewPostsReceived(posts: List<Post>) {
        _uiState.value = if (posts.isEmpty()) {
            HomeUiState.Error(EMPTY_POSTS)
        } else HomeUiState.NewPosts(posts.toUiPost { onRepostClick(it) })
    }

    private fun onRepostClick(post: Post) {
        _uiState.value = HomeUiState.Repost(post)
    }

    companion object {
        private const val GENERIC_ERROR = "Generic Error"
        private const val POST_LENGTH_EXCEEDED = "Post exceeded limited characters!"
        private const val EMPTY_POSTS = "Empty Posts"
        private const val EMPTY = ""
    }
}