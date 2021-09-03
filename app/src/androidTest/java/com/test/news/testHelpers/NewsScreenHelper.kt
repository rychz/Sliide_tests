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
import com.test.news.customs.getElementFromMatchAtPosition
import com.test.news.customs.getRecyclerViewChildCount
import com.test.news.customs.retrieveNewsUrl
import com.test.news.customs.validateImageHasDrawable
import com.test.news.utils.*
import org.hamcrest.Matchers.allOf

class NewsScreenHelper {
    private val maxHorizontalNews = 3
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
        repeat(getRecyclerViewChildCount(mainRecyclerView)) { mainIterator ->
            repeat(maxHorizontalNews) {
                mainRecyclerView.onRecyclerViewElement(mainIterator, validateImageHasDrawable())
                mainRecyclerView.onRecyclerViewElement(mainIterator, swipeLeft())
            }
        }
    }

    fun openNewsAndValidateBrowserIntent(newsRow: Int, newsPos: Int) {
        try {
            Intents.init()
            val url = getUrlOfSecondNewsFromRow(newsRow, newsPos)
            val expectedIntent = allOf(
                IntentMatchers.hasAction(Intent.ACTION_VIEW),
                IntentMatchers.hasData(url)
            )
            Intents.intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))
            clickOnNewsAt(newsRow, newsPos)
            Intents.intended(expectedIntent)
        } finally {
            Intents.release()
        }
    }

    private fun getUrlOfSecondNewsFromRow(newsRow: Int, newsPos: Int): String {
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

    private fun clickOnNewsAt(newsRow: Int, newsPos: Int) {
        repeat(newsPos) {
            mainRecyclerView.onRecyclerViewElement(newsRow, swipeLeft())
        }
        // Wait 300ms for swipe animation
        // to finish before clicking on news
        Thread.sleep(300)
        mainRecyclerView.onRecyclerViewElement(newsRow, click())

    }
}