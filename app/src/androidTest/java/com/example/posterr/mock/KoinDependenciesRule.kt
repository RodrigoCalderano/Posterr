package com.example.posterr.mock

import androidx.test.platform.app.InstrumentationRegistry
import com.example.domain.di.domainModule
import com.example.home.di.homeModule
import com.example.posterr.PosterrApp
import com.example.posterr.mockdi.mockedModule
import com.example.profile.di.profileModule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin

class KoinDependenciesRule : TestWatcher() {

    override fun starting(description: Description?) {
        stopKoin()

        (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as? PosterrApp)?.startKoin()
        loadKoinModules(
            listOf(
                homeModule,
                profileModule,
                domainModule,
                mockedModule,
            )
        )
    }
}