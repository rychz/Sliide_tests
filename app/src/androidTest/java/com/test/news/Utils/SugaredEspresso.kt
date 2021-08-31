package com.test.news.Utils

import android.content.Context
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry

val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

fun ViewInteraction.click(): ViewInteraction =
    this.perform(ViewActions.click())

fun ViewInteraction.typeText(text: String): ViewInteraction =
    this.perform(ViewActions.typeText(text))

fun ViewInteraction.isDisplayed(): ViewInteraction =
    this.check(matches(ViewMatchers.isDisplayed()))
