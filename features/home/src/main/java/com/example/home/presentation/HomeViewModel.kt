package com.example.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.GetFeedUseCase
import com.example.models.domain.Post
import com.example.ui.mappers.toUiPost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

internal class HomeViewModel(
    getFeedUseCase: GetFeedUseCase,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
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

    fun onRepostConfirmed(post: Post, quoteText: String?) {
        println("$post \n $quoteText")
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
        private const val EMPTY_POSTS = "Empty Posts"
    }
}