package com.test.news.testHelpers

import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R
import com.test.news.customs.*
import com.test.news.utils.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class NewsScreenHelper {
    private val mainRecyclerView = onView(withId(R.id.recyclerViewNews))
    private val offlineModeError = onView(withId(R.id.textViewError))
    private val newsScreenTitle = onView(
        allOf(
            isDescendantOfA(withId(R.id.action_bar)),
            withText(R.string.app_name)
        )
    )

    fun assertFailedToLoadNewsError() {
        offlineModeError.isDisplayed()
        offlineModeError.hasText(R.string.news_failed_to_load_message)
    }

    fun assertNewsScreenIsOpen() {
        device.waitForElementWithTextToBeVisible(R.string.app_name)
        newsScreenTitle.isDisplayed()
    }

    fun assertMainRecyclerViewIsDisplayed() {
        device.waitForElementWithIdToBeVisible(R.id.recyclerViewNews)
        newsScreenTitle.isDisplayed()
        mainRecyclerView.isDisplayed()
    }

    fun assertNewsAreLoaded() {
        // Loop through all elements in main recycler view
        repeat(getRecyclerViewChildCount(mainRecyclerView)) { mainIterator ->
            // Loop through all elements in horizontal recycler view
            repeat(getNestedRecyclerViewChildCount(mainRecyclerView, mainIterator)) {
                mainRecyclerView.onRecyclerViewElement(mainIterator, validateImageHasDrawable())
                mainRecyclerView.onRecyclerViewElement(mainIterator, swipeLeft())
            }
        }
    }

    fun openNewsAndValidateBrowserIntent() {
        // Loop through all elements in main recycler view
        repeat(getRecyclerViewChildCount(mainRecyclerView)) { mainIterator ->
            // Loop through all elements in horizontal recycler view
            repeat(getNestedRecyclerViewChildCount(mainRecyclerView, mainIterator)) {
                try {
                    Intents.init()
                    val url = getUrlOfNewsAt(mainIterator, it)
                    val expectedIntent = getExpectedBrowserIntent(url)
                    Intents.intending(expectedIntent)
                        .respondWith(Instrumentation.ActivityResult(0, null))
                    mainRecyclerView.onRecyclerViewElement(mainIterator, click())
                    Intents.intended(expectedIntent)
                    mainRecyclerView.onRecyclerViewElement(mainIterator, swipeLeft())
                    // Wait 300ms for swipe animation to finish
                    Thread.sleep(300)
                } finally {
                    Intents.release()
                }
            }
        }

    }

    private fun getUrlOfNewsAt(newsRow: Int, newsPos: Int): String {
        return retrieveNewsUrl(
            onView(
                getElementFromMatchAtPosition(
                    withId(R.id.recyclerViewImageWidget),
                    newsRow
                )
            ),
            newsPos
        )
    }

    private fun getExpectedBrowserIntent(url: String): Matcher<Intent>? {
        return allOf(
            IntentMatchers.hasAction(Intent.ACTION_VIEW),
            IntentMatchers.hasData(url)
        )
    }

}