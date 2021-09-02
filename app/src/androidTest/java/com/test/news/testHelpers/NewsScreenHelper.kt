package com.test.news.testHelpers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R
import com.test.news.utils.device
import com.test.news.utils.isDisplayed
import com.test.news.utils.onRecyclerViewElement
import com.test.news.utils.waitForElementWithIdToBeVisible
import org.hamcrest.Matchers.allOf

class NewsScreenHelper {

    private val mainRecyclerView = onView(withId(R.id.recyclerViewNews))
    private val horizontalRecyclerView = onView(withId(R.id.recyclerViewImageWidget))

    private val newsScreenTitle = onView(
        allOf(
            isDescendantOfA(withId(R.id.action_bar)),
            withText(R.string.app_name)
        )
    )

    fun assertNewsScreenIsOpen() {
        device.waitForElementWithIdToBeVisible(R.id.recyclerViewNews)
        newsScreenTitle.isDisplayed()
    }

    fun assertNewsAreLoaded() {
        repeat(6) {
            Thread.sleep(200)
            mainRecyclerView.onRecyclerViewElement(it, swipeLeft())
        }
    }

    fun openNewsAtPosition() {
        mainRecyclerView.onRecyclerViewElement(0, click())
    }
}