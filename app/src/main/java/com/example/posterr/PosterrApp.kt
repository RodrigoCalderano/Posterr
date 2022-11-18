package com.example.posterr

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.domain.repointerfaces.PostsRepository
import com.example.domain.repointerfaces.UserRepository
import com.example.home.di.homeModule
import com.example.models.domain.Post
import com.example.models.domain.User
import com.example.profile.di.profileModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

class PosterrApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PosterrApp)
            modules(
                listOf(
                    homeModule,
                    profileModule,
                    dataModule,
                    domainModule,
                )
            )
        }

        demo()
    }

    private fun demo() {
        // Just for Demo purposes: For this app it is considered that the user is already logged
        CoroutineScope(Dispatchers.IO).launch {
            val userRepository = getKoin().get<UserRepository>()
            val postsRepository = getKoin().get<PostsRepository>()

            userRepository.createUser(demoUsers().first())
            postsRepository.insertPosts(demoPosts())
        }
    }

    private fun demoUsers() = listOf(
        User(
            userName = "Rodrigo Calderano",
            profileDataJoined = "March 25, 2021",
            profileOriginalPosts = 0,
            profileReposts = 0,
            profileQuotePosts = 0
        ),
        User(
            userName = "Rodrigo Barbacovi",
            profileDataJoined = "March 24, 2021",
            profileOriginalPosts = 1,
            profileReposts = 0,
            profileQuotePosts = 0
        ),
        User(
            userName = "Jorge",
            profileDataJoined = "March 23, 2021",
            profileOriginalPosts = 0,
            profileReposts = 1,
            profileQuotePosts = 0
        ),
        User(
            userName = "João",
            profileDataJoined = "March 22, 2021",
            profileOriginalPosts = 0,
            profileReposts = 0,
            profileQuotePosts = 1
        ),
    )

    private fun demoPosts() = listOf(
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