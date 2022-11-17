package com.example.ui.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R
import com.example.ui.models.QuotePostUi

internal class QuotePostViewHolder private constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val quotePostOriginalPostAuthor: TextView =
        itemView.findViewById(R.id.quotePostOriginalPostAuthor)
    private val quotePostUserName: TextView = itemView.findViewById(R.id.quotePostUserName)
    private val quotePostTextView: TextView = itemView.findViewById(R.id.quotePostTextView)
    private val quotePostText: TextView = itemView.findViewById(R.id.quotePostText)
    private val quotePostButton: ImageButton = itemView.findViewById(R.id.quotePostButton)

    fun bind(item: QuotePostUi) = with(item) {
        quotePostOriginalPostAuthor.text = item.originalPostAuthor
        quotePostUserName.text = item.userNameAuthor
        quotePostTextView.text = item.originalPostText
        quotePostText.text = item.additionalQuoteText
        quotePostButton.setOnClickListener { repostClickAction() }
    }

    companion object {
        fun from(parent: ViewGroup): QuotePostViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.ui_quote_post_layout, parent, false)
            return QuotePostViewHolder(view)
        }
    }
}