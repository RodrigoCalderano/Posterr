package com.example.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.AddPostResult
import com.example.domain.usecases.AddPostUseCase
import com.example.domain.usecases.GetUserUseCase
import com.example.models.domain.Post
import com.example.profile.domain.GetPostsFromUserUseCase
import com.example.profile.presentation.ProfileFragment.Companion.MAX_CHARACTERS
import com.example.ui.mappers.toUiPost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getPostsFromUserUseCase: GetPostsFromUserUseCase,
    private val addPostUseCase: AddPostUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableLiveData<ProfileUiState>(ProfileUiState.Loading)
    val uiState: LiveData<ProfileUiState> = _uiState

    init {
        launch {
            refreshUserData().run {
                _uiState.postValue(ProfileUiState.UpdateScreenSuccessState(listOf(), this))
                fetchPosts(userName)
            }
        }
    }

    private suspend fun refreshUserData() = getUserUseCase.invoke()

    private fun fetchPosts(userName: String) {
        getPostsFromUserUseCase(userName)
            .flowOn(dispatcher)
            .onStart { _uiState.value = ProfileUiState.Loading }
            .onEach { posts -> onNewPostsReceived(posts) }
            .catch { _uiState.value = ProfileUiState.Error(GENERIC_ERROR) }
            .launchIn(viewModelScope)
    }

    fun onRepostConfirmed(post: Post, quoteText: String) = launch {
        addPostUseCase(
            post.copy(
                additionalQuoteText = quoteText,
                type = if (quoteText.isEmpty()) Post.Companion.PostType.REPOST else Post.Companion.PostType.QUOTE_POST,
            )
        ).run {
            if (this == AddPostResult.EXCEEDED_DAILY_LIMIT)
                _uiState.postValue(ProfileUiState.Toast(MAXIMUM_POSTS_EXCEEDED))
        }
    }

    fun onPostButtonClicked(postText: String) {
        if (postText.length > MAX_CHARACTERS)
            _uiState.value = ProfileUiState.Toast(POST_LENGTH_EXCEEDED)
        else addPost(postText)
    }

    private fun addPost(postText: String) = launch {
        addPostUseCase(
            Post(
                originalPostText = postText,
                originalPostAuthor = EMPTY,
                type = Post.Companion.PostType.ORIGINAL_POST,
                userNameAuthor = EMPTY
            )
        ).run {
            if (this == AddPostResult.EXCEEDED_DAILY_LIMIT)
                _uiState.postValue(ProfileUiState.Toast(MAXIMUM_POSTS_EXCEEDED))
        }
    }

    private fun onNewPostsReceived(posts: List<Post>) = launch {
        val state = if (posts.isEmpty()) {
            ProfileUiState.Error(EMPTY_POSTS)
        } else ProfileUiState.UpdateScreenSuccessState(
            posts.toUiPost { onRepostClick(it) },
            refreshUserData()
        )
        _uiState.postValue(state)
    }

    private fun onRepostClick(post: Post) {
        _uiState.value = ProfileUiState.Repost(post)
    }

    private fun launch(block: suspend () -> Unit) =
        viewModelScope.launch(context = dispatcher) { block() }

    companion object {
        private const val GENERIC_ERROR = "Generic Error"
        private const val POST_LENGTH_EXCEEDED = "Post exceeded limited characters!"
        private const val MAXIMUM_POSTS_EXCEEDED = "Posts exceeded daily limited (5)!"
        private const val EMPTY_POSTS = "Empty Posts"
        private const val EMPTY = ""
    }
}