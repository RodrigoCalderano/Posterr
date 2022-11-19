package com.example.posterr.assertions

import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class FirstMatcher(
    private val expected: Matcher<View?>
) : TypeSafeMatcher<View?>() {

    private var first = false

    override fun matchesSafely(item: View?): Boolean {
        return if (expected.matches(item) && !first) {
            true.also { first = it }
        } else false
    }

    override fun describeTo(description: Description) {
        description.appendText(String.format(DESCRIBE_TO_TEMPLATE, expected))
    }

    companion object {
        private const val DESCRIBE_TO_TEMPLATE = "Matcher.first(%s)"

        fun first(expected: Matcher<View?>): Matcher<View?> = FirstMatcher(expected)
    }
}