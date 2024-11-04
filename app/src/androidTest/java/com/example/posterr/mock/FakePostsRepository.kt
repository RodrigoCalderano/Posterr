package com.example.posterr.mock

import com.example.domain.repointerfaces.PostsRepository
import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePostsRepository : PostsRepository {
    override fun getAllPosts(): Flow<List<Post>> {
        return flowOf(mockedPosts)
    }

    override fun getAllPostsFromUser(userName: String): Flow<List<Post>> {
        return flowOf(mockedPosts)
    }

    override fun getTimeOfFifthLastPost(userName: String): Long? {
        return null
    }

    override fun insertPosts(posts: List<Post>) {
    }

    companion object {
        val mockedPosts = listOf(
            Post(
                originalPostText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vel tellus sed sem viverra lobortis. Aliquam nec mollis nisl. Ut varius maximus bibendum. Sed in venenatis orci. Nulla orci metus, ultrices nec vestibulum vitae, placerat ac erat. In at ornare enim, nec pretium nisl. Donec volutpat nisi ligula.",
                originalPostAuthor = "Rodrigo Barbacovi",
                type = Post.Companion.PostType.ORIGINAL_POST,
                userNameAuthor = "Rodrigo Barbacovi"
            ),
            Post(
                originalPostText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vel tellus sed sem viverra lobortis. Aliquam nec mollis nisl. Ut varius maximus bibendum. Sed in venenatis orci. Nulla orci metus, ultrices nec vestibulum vitae, placerat ac erat. In at ornare enim, nec pretium nisl. Donec volutpat nisi ligula.",
                originalPostAuthor = "Rodrigo Barbacovi",
                userNameAuthor = "Jorge",
                type = Post.Companion.PostType.REPOST
            ),
            Post(
                originalPostText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vel tellus sed sem viverra lobortis. Aliquam nec mollis nisl. Ut varius maximus bibendum. Sed in venenatis orci. Nulla orci metus, ultrices nec vestibulum vitae, placerat ac erat. In at ornare enim, nec pretium nisl. Donec volutpat nisi ligula.",
                originalPostAuthor = "Rodrigo Barbacovi",
                userNameAuthor = "João",
                additionalQuoteText = "Ohh I really loved that post!!!!",
                type = Post.Companion.PostType.QUOTE_POST
            ),
        )
    }
}