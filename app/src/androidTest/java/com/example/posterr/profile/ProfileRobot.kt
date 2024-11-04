package com.example.posterr.profile

import androidx.annotation.IdRes
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.posterr.R
import com.example.posterr.assertions.FirstMatcher.Companion.first
import com.example.posterr.assertions.RecyclerViewItemCountAssertion
import com.example.posterr.mock.FakePostsRepository.Companion.mockedPosts
import com.example.posterr.mock.FakeUserRepository.Companion.stubUser
import com.example.profile.presentation.ProfileFragment
import org.hamcrest.Matchers
import com.example.profile.R as ProfileR

class ProfileRobot {

    fun launchFragment() = apply {
        launchFragmentInContainer<ProfileFragment>(null, R.style.Theme_Posterr)
    }

    fun thenProfileIsShown() = apply {
        checkViewContainsText(stubUser.userName)
        checkViewContainsText(stubUser.profileDataJoined)
        checkViewContainsText(stubUser.profileOriginalPosts.toString())
        checkViewContainsText(stubUser.profileReposts.toString())
        checkViewContainsText(stubUser.profileQuotePosts.toString())
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

    private fun checkViewContainsText(text: String): ProfileRobot {
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

    private fun checkRecyclerViewHasItems(@IdRes recyclerViewId: Int, expected: Int): ProfileRobot {
        onView(ViewMatchers.withId(recyclerViewId))
            .check(RecyclerViewItemCountAssertion(expected))
        return this
    }

    companion object {
        private const val TITLE = "Profile"
    }
}
