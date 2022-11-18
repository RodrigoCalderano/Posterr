package com.example.ui.mappers

import com.example.models.domain.Post
import com.example.ui.models.OriginalPostUi
import com.example.ui.models.QuotePostUi
import com.example.ui.models.RepostUi
import com.example.ui.models.UiPost
import java.io.InvalidObjectException

fun List<Post>.toUiPost(repostCallback: (Post) -> Unit): List<UiPost> {
    return map { post ->
        when (post.type) {
            Post.Companion.PostType.ORIGINAL_POST -> post.toOriginalPostUi(repostCallback)
            Post.Companion.PostType.REPOST -> post.toRepostUi(repostCallback)
            Post.Companion.PostType.QUOTE_POST -> post.toQuotePostUi(repostCallback)
        }
    }
}

private fun Post.toOriginalPostUi(repostCallback: (Post) -> Unit) = OriginalPostUi(
    originalPostText = originalPostText,
    originalPostAuthor = originalPostAuthor,
    repostClickAction = { repostCallback(this) },
)

private fun Post.toRepostUi(repostCallback: (Post) -> Unit): RepostUi {
    return RepostUi(
        originalPostText = originalPostText,
        originalPostAuthor = originalPostAuthor,
        repostClickAction = { repostCallback(this) },
        userNameAuthor = userNameAuthor
    )
}

private fun Post.toQuotePostUi(repostCallback: (Post) -> Unit): QuotePostUi {
    additionalQuoteText?.let { verifiedAdditionalQuoteText ->
        return QuotePostUi(
            originalPostText = originalPostText,
            originalPostAuthor = originalPostAuthor,
            repostClickAction = { repostCallback(this) },
            userNameAuthor = userNameAuthor,
            additionalQuoteText = verifiedAdditionalQuoteText,
        )
    }
    throw InvalidObjectException("QuotePostUi with missing attributes")
}