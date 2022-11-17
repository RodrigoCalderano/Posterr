package com.example.home.domain

import com.example.models.domain.Post
import com.example.models.domain.Post.Companion.PostType.ORIGINAL_POST
import com.example.models.domain.Post.Companion.PostType.QUOTE_POST
import com.example.models.domain.Post.Companion.PostType.REPOST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class GetFeedUseCaseImpl : GetFeedUseCase {
    override fun invoke(): Flow<List<Post>> {
        return flow {
            kotlinx.coroutines.delay(3000)
            emit(
                listOf(
                    Post(
                        originalPostText = "========= bibendum vitae velit.",
                        originalPostAuthor = "Rodrigo",
                        type = ORIGINAL_POST
                    ),
                    Post(
                        originalPostText = "Ut ac fermentum quam. Donec interdum quis sem sed porta. Etiam vel nisl et nulla ullamcorper interdum sit amet eget dui. Nulla eleifend sodales orci quis accumsan. Morbi bibendum luctus erat, vitae aliquet arcu feugiat sed. Vestibulum a risus non mauris blandit tempus vel sit amet risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer non mi urna. Phasellus maximus euismod eros, sit amet cursus turpis consectetur ut. Phasellus nibh diam, suscipit ut finibus tincidunt, bibendum vitae velit.",
                        originalPostAuthor = "João",
                        userNameAuthor = "Matheus",
                        type = REPOST
                    ),
                    Post(
                        originalPostText = "iverra dolor vitae, fermentum quam. Donec interdum quis sem sed porta. Etiam vel nisl et nulla ullamcorper interdum sit amet eget dui. Nulla eleifend sodales orci quis accumsan. Morbi bibendum luctus erat, vitae aliquet arcu feugiat sed. Vestibulum a risus non mauris blandit tempus vel sit amet risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer non mi urna. Phasellus maximus euismod eros, sit amet cursus turpis consectetur ut. Phasellus nibh diam, suscipit ut finibus tincidunt, bibendum vitae velit.",
                        originalPostAuthor = "lar",
                        userNameAuthor = "RodrigoQuotador",
                        additionalQuoteText = "Achei muito interessante esse post!!!!",
                        type = QUOTE_POST
                    ),
                    Post(
                        originalPostText = "aaaaaaaaaaaaUt ac lacus mollis, viverra dolor vitae, fermentum quam. Donec interdum quis sem sed porta. Etiam vel nisl et nulla ullamcorper interdum sit amet eget dui. Nulla eleifend sodales orci quis accumsan. Morbi bibendum luctus erat, vitae aliquet arcu feugiat sed. Vestibulum a risus non mauris blandit tempus vel sit amet risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer non mi urna. Phasellus maximus euismod eros, sit amet cursus turpis consectetur ut. Phasellus nibh diam, suscipit ut finibus tincidunt, bibendum vitae velit.",
                        originalPostAuthor = "Rodrigo",
                        type = ORIGINAL_POST
                    ),
                )
            )
        }
    }
}