package com.test.news.tests

import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import com.test.news.BaseEspressoTest
import org.hamcrest.Matchers.allOf
import org.junit.Test


class NewsTest : BaseEspressoTest() {

    /**
     * Espresso tests that covers user story: As a user I want to see my news
     */

    @Test
    fun asd() {
        loginScreen {
            fillUserName()
            fillPassword()
            clickLoginButton()
        }
        newsScreen {
            assertNewsScreenIsOpen()
            assertNewsAreLoaded()
        }
    }

    @Test
    fun qwe() {
        loginScreen {
            fillUserName()
            fillPassword()
            clickLoginButton()
        }
        newsScreen {
            assertNewsScreenIsOpen()
            Intents.init()
            openNewsAtPosition()
            val expectedIntent = allOf(hasAction(Intent.ACTION_VIEW))
            intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))
            intended(expectedIntent)
            Intents.release()
        }
    }

}