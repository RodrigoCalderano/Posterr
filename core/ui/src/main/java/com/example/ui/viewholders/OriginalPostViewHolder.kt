package com.example.ui.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R
import com.example.ui.models.OriginalPostUi

internal class OriginalPostViewHolder private constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val originalPostUserName: TextView = itemView.findViewById(R.id.originalPostUserName)
    private val originalPostTextView: TextView = itemView.findViewById(R.id.originalPostTextView)
    private val originalPostRepostButton: ImageButton =
        itemView.findViewById(R.id.originalPostRepostButton)

    fun bind(item: OriginalPostUi) = with(item) {
        originalPostUserName.text = item.originalPostAuthor
        originalPostTextView.text = item.originalPostText
        originalPostRepostButton.setOnClickListener { repostClickAction() }
    }

    companion object {
        fun from(parent: ViewGroup): OriginalPostViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.ui_original_post_layout, parent, false)
            return OriginalPostViewHolder(view)
        }
    }
}