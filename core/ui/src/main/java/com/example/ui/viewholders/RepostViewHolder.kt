package com.example.ui.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R
import com.example.ui.models.RepostUi

internal class RepostViewHolder private constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val repostOriginalPostAuthor: TextView =
        itemView.findViewById(R.id.repostOriginalPostAuthor)
    private val repostUserName: TextView = itemView.findViewById(R.id.repostUserName)
    private val repostTextView: TextView = itemView.findViewById(R.id.repostTextView)
    private val repostButton: ImageButton =
        itemView.findViewById(R.id.repostButton)

    fun bind(item: RepostUi) = with(item) {
        repostOriginalPostAuthor.text = item.originalPostAuthor
        repostUserName.text = item.userNameAuthor
        repostTextView.text = item.originalPostText
        repostButton.setOnClickListener {
            repostClickAction(item)
        }
    }

    companion object {
        fun from(parent: ViewGroup): RepostViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.ui_repost_layout, parent, false)
            return RepostViewHolder(view)
        }
    }
}