package com.test.news.utils

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.test.news.R
import com.test.news.features.news.presentation.adapter.NewsImagesAdapter
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import java.lang.RuntimeException


fun validateImageHasDrawable(): ViewAction {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return allOf(
                instanceOf(RecyclerView::class.java),
                isDisplayed()
            )
        }

        override fun getDescription(): String {
            return "Validate that ImageView has drawable assigned to it."
        }

        override fun perform(uiController: UiController, view: View) {
            val iv = view.findViewById<View>(R.id.imageView) as ImageView
            iv.drawable ?: throw AssertionError("$iv has no drawable assigned!")
        }
    }
}

fun retrieveNewsUrl(matcher: ViewInteraction, newsPos: Int): String {
    var targetNewsUrl = "no url"
    matcher.perform(object : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return allOf(
                instanceOf(RecyclerView::class.java),
                isDisplayed()
            )
        }

        override fun getDescription(): String {
            return "Return URL of news in RecyclerView."
        }

        override fun perform(uiController: UiController, view: View) {
            val rv = view as RecyclerView
            val newsAdapter = rv.adapter as NewsImagesAdapter
            val urlsList = newsAdapter.getImageUrlsList()
            if (urlsList.size > newsPos) {
                targetNewsUrl = urlsList[newsPos]
            } else {
                throw RuntimeException("Demanded news position was out of URLs list size")
            }
        }
    })

    return targetNewsUrl
}

fun getRecyclerViewChildCount(matcher: ViewInteraction): Int {
    var childCount: Int = -1
    matcher.perform(object : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return allOf(
                instanceOf(RecyclerView::class.java),
                isDisplayed()
            )
        }

        override fun getDescription(): String {
            return "Return RecyclerView child count."
        }

        override fun perform(uiController: UiController, view: View) {
            val rv = view as RecyclerView
            childCount = rv.adapter?.itemCount ?: throw NullPointerException("Adapter was null")
        }
    })

    return childCount
}




