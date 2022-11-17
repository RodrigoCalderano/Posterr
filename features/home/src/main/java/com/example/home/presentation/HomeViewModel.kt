package com.example.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.GetFeedUseCase
import com.example.models.domain.Post
import com.example.ui.mappers.toUiPost
import com.example.ui.models.UiPost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

internal class HomeViewModel(
    getFeedUseCase: GetFeedUseCase,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _posts = MutableLiveData<MutableList<UiPost>>(mutableListOf())
    val posts: LiveData<MutableList<UiPost>> = _posts

    private val _progressBarVisibility = MutableLiveData(true)
    val progressBarVisibility: LiveData<Boolean> = _progressBarVisibility

    init {
        getFeedUseCase()
            .flowOn(dispatcher)
            .onStart { _progressBarVisibility.value = true }
            .onEach { posts -> onNewPostsReceived(posts) }
            .onCompletion { _progressBarVisibility.value = false }
            .launchIn(viewModelScope)
    }

    private fun onNewPostsReceived(posts: List<Post>) {
        _posts.value = posts.toUiPost { onRepostClick(it) }.toMutableList()
    }

    private fun onRepostClick(post: Post) {
        println("clicked on $post")
    }
}