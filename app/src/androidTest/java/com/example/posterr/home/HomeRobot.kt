package com.example.posterr.home

import androidx.annotation.IdRes
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.home.presentation.HomeFragment
import com.example.posterr.R
import com.example.posterr.assertions.FirstMatcher.Companion.first
import com.example.posterr.assertions.RecyclerViewItemCountAssertion
import com.example.posterr.mock.MockPostsRepository.Companion.mockedPosts
import org.hamcrest.Matchers
import com.example.profile.R as ProfileR

class HomeRobot {

    fun launchFragment() = apply {
        launchFragmentInContainer<HomeFragment>(null, R.style.Theme_Posterr)
    }

    fun thenPostsAreShown() = apply {
        mockedPosts.forEach {
            checkViewContainsText(it.originalPostText)
            checkViewContainsText(it.originalPostAuthor)
        }
    }

    fun thenTitleMustBeDisplayed() = apply {
        checkViewContainsText(TITLE)
    }

    fun thenShouldLoadUsersList() = apply {
        checkRecyclerViewHasItems(ProfileR.id.rvPosts, mockedPosts.size)
    }

    private fun checkViewContainsText(text: String): HomeRobot {
        onView(
            first(
                Matchers.allOf(
                    ViewMatchers.withText(text),
                    ViewMatchers.isDisplayed()
                )
            )
        ).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        return this
    }

    private fun checkRecyclerViewHasItems(@IdRes recyclerViewId: Int, expected: Int): HomeRobot {
        onView(ViewMatchers.withId(recyclerViewId))
            .check(RecyclerViewItemCountAssertion(expected))
        return this
    }

    companion object {
        private const val TITLE = "Home"
    }
}
