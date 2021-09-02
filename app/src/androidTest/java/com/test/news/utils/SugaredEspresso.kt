package com.test.news.utils

import android.app.Instrumentation
import android.app.UiAutomation
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.test.news.R


// General
val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
val uiAutomation : UiAutomation = instrumentation.uiAutomation
val appContext: Context = instrumentation.targetContext
val device: UiDevice = UiDevice.getInstance(instrumentation)
val targetPackage: String = appContext.packageName


// Espresso
fun ViewInteraction.click(): ViewInteraction =
    this.perform(ViewActions.click())

fun ViewInteraction.typeText(text: String): ViewInteraction =
    this.perform(ViewActions.typeText(text))

fun ViewInteraction.isDisplayed(): ViewInteraction =
    this.check(matches(ViewMatchers.isDisplayed()))

fun ViewInteraction.onRecyclerViewElement(position: Int, action: ViewAction): ViewInteraction =
    this.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, action))


// UiAutomator
const val UI_AUTOMATOR_TIMEOUT = 5L

fun UiDevice.waitForElementWithTextToBeVisible(elementText : Int) {
    this.wait(Until.hasObject(By.text(appContext.getString(R.string.app_name))), UI_AUTOMATOR_TIMEOUT)
}

fun UiDevice.waitForElementWithTextToBeVisible(elementText : String) {
    this.wait(Until.hasObject(By.text(elementText)), UI_AUTOMATOR_TIMEOUT)
}

fun UiDevice.waitForElementWithIdToBeVisible(elementId : Int) {
    this.wait(Until.hasObject(By.res(targetPackage, appContext.getString(elementId))), UI_AUTOMATOR_TIMEOUT)
}