package com.example.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.models.OriginalPostUi
import com.example.ui.models.QuotePostUi
import com.example.ui.models.RepostUi
import com.example.ui.models.UiPost
import com.example.ui.models.UiPost.Companion.ViewType.ORIGINAL_POST_UI
import com.example.ui.viewholders.OriginalPostViewHolder

class PostsListAdapter : ListAdapter<UiPost, RecyclerView.ViewHolder>(DefaultDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ORIGINAL_POST_UI.ordinal -> OriginalPostViewHolder.from(parent)
            else -> {
                throw InvalidViewTypeException()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is OriginalPostUi -> (holder as? OriginalPostViewHolder)?.bind(item)
            is QuotePostUi -> TODO()
            is RepostUi -> TODO()
        }
    }

    private companion object {
        class InvalidViewTypeException : IllegalStateException("Invalid view type for holder")
    }
}