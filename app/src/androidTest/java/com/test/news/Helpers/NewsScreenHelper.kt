package com.test.news.Helpers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R
import com.test.news.Utils.isDisplayed
import org.hamcrest.Matchers.allOf

class NewsScreenHelper {

    private val newsScreenTitle = onView(allOf(
        isDescendantOfA(withId(R.id.action_bar)),
        withText(R.string.app_name)
    ))

    fun assertNewsScreenIsOpen() {
        newsScreenTitle.isDisplayed()
    }
}