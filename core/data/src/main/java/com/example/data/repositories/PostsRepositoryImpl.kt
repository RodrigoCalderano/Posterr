package com.example.data.repositories

import com.example.models.domain.Post
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class PostsRepositoryImpl : PostsRepository {
    override fun getAllPosts(): Flow<List<Post>> {
        return flow {
            delay(3000)
            emit(
                listOf(
                    Post(
                        originalPostText = "========= bibendum vitae velit.",
                        originalPostAuthor = "Rodrigo",
                        type = Post.Companion.PostType.ORIGINAL_POST
                    ),
                    Post(
                        originalPostText = "Ut ac fermentum quam. Donec interdum quis sem sed porta. Etiam vel nisl et nulla ullamcorper interdum sit amet eget dui. Nulla eleifend sodales orci quis accumsan. Morbi bibendum luctus erat, vitae aliquet arcu feugiat sed. Vestibulum a risus non mauris blandit tempus vel sit amet risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer non mi urna. Phasellus maximus euismod eros, sit amet cursus turpis consectetur ut. Phasellus nibh diam, suscipit ut finibus tincidunt, bibendum vitae velit.",
                        originalPostAuthor = "João",
                        userNameAuthor = "Matheus",
                        type = Post.Companion.PostType.REPOST
                    ),
                    Post(
                        originalPostText = "iverra dolor vitae, fermentum quam. Donec interdum quis sem sed porta. Etiam vel nisl et nulla ullamcorper interdum sit amet eget dui. Nulla eleifend sodales orci quis accumsan. Morbi bibendum luctus erat, vitae aliquet arcu feugiat sed. Vestibulum a risus non mauris blandit tempus vel sit amet risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer non mi urna. Phasellus maximus euismod eros, sit amet cursus turpis consectetur ut. Phasellus nibh diam, suscipit ut finibus tincidunt, bibendum vitae velit.",
                        originalPostAuthor = "lar",
                        userNameAuthor = "RodrigoQuotador",
                        additionalQuoteText = "Achei muito interessante esse post!!!!",
                        type = Post.Companion.PostType.QUOTE_POST
                    ),
                    Post(
                        originalPostText = "aaaaaaaaaaaaUt ac lacus mollis, viverra dolor vitae, fermentum quam. Donec interdum quis sem sed porta. Etiam vel nisl et nulla ullamcorper interdum sit amet eget dui. Nulla eleifend sodales orci quis accumsan. Morbi bibendum luctus erat, vitae aliquet arcu feugiat sed. Vestibulum a risus non mauris blandit tempus vel sit amet risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer non mi urna. Phasellus maximus euismod eros, sit amet cursus turpis consectetur ut. Phasellus nibh diam, suscipit ut finibus tincidunt, bibendum vitae velit.",
                        originalPostAuthor = "Rodrigo",
                        type = Post.Companion.PostType.ORIGINAL_POST
                    ),
                )
            )
        }
    }
}