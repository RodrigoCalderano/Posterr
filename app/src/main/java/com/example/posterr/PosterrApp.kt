package com.example.posterr

import android.app.Application
import com.example.data.di.dataModule
import com.example.data.repositories.PostsRepository
import com.example.home.di.homeModule
import com.example.models.domain.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PosterrApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PosterrApp)
            modules(
                listOf(
                    homeModule,
                    dataModule,
                )
            )
        }

        // Just for demo purposes:
        // val repo = getKoin().get<PostsRepository>()
        //
        // CoroutineScope(Dispatchers.IO).launch {
        //     repo.insertPost(
        //         Post(
        //             originalPostText = "meu texto111",
        //             originalPostAuthor = "L",
        //             type = Post.Companion.PostType.ORIGINAL_POST
        //         ),
        //     )
        // }
    }
}