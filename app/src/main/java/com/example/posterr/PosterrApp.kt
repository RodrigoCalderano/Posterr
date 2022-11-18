package com.example.posterr

import android.app.Application
import com.example.data.dao.UserDao
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.domain.repointerfaces.PostsRepository
import com.example.domain.repointerfaces.UserRepository
import com.example.home.di.homeModule
import com.example.models.domain.Post
import com.example.models.domain.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
                    dataModule,
                    domainModule,
                )
            )
        }

        // demo()
        // demo2()
        demo3()
    }

    private fun demo3() {
        val dao = getKoin().get<UserDao>()
        CoroutineScope(Dispatchers.IO).launch {
            val t = dao.retrieveUser()
        }
    }

    private fun demo2() {
        val repo = getKoin().get<UserRepository>()
        CoroutineScope(Dispatchers.IO).launch {
            repo.createUser(
                User(
                    userName = "Rodrigo",
                    profileDataJoined = "23, 10 / 2000",
                    profileOriginalPosts = 0,
                    profileReposts = 0,
                    profileQuotePosts = 0
                )
            )
        }
    }

    private fun demo() {
        // Just for demo purposes:
        val repo = getKoin().get<PostsRepository>()

        CoroutineScope(Dispatchers.IO).launch {
            repo.insertPosts(
                listOf(
                    Post(
                        originalPostText = "========= bibendum vitae velit.",
                        originalPostAuthor = "Rodrigo",
                        type = Post.Companion.PostType.ORIGINAL_POST,
                        userNameAuthor = "Rodrigo"
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
                        type = Post.Companion.PostType.ORIGINAL_POST,
                        userNameAuthor = "Rodrigo"
                    ),
                )
            )
        }
    }
}