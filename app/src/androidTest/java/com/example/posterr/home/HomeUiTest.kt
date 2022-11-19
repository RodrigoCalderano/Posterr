package com.example.posterr.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.posterr.mock.KoinDependenciesRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeUiTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val koinDependenciesRule = KoinDependenciesRule()

    private lateinit var homeRobot: HomeRobot

    @Before
    fun setup() {
        homeRobot = HomeRobot()
    }

    @Test
    fun checkHomeIsDisplayed() {
        homeRobot
            .launchFragment()
            .thenTitleMustBeDisplayed()
            .thenPostsAreShown()
            .thenShouldLoadUsersList()
    }
}