package com.test.news

import androidx.test.espresso.Espresso
import androidx.test.rule.ActivityTestRule
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.testHelpers.LoginScreenHelper
import com.test.news.testHelpers.NewsScreenHelper
import org.junit.Assert.assertTrue
import org.junit.Rule

open class BaseEspressoTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(LoginActivity::class.java)

    protected fun loginScreen(func: LoginScreenHelper.() -> Unit) =
        LoginScreenHelper().apply { func() }

    protected fun newsScreen(func: NewsScreenHelper.() -> Unit) =
        NewsScreenHelper().apply { func() }

    fun closeCurrentActivity() {
        Espresso.pressBackUnconditionally()
        assertTrue(activityTestRule.activity.isFinishing)
    }

    fun startActivity() {
        activityTestRule.launchActivity(null)
    }
}
