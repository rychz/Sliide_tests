package com.test.news

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.test.news.Helpers.LoginScreenHelper
import com.test.news.Helpers.NewsScreenHelper
import com.test.news.features.login.presentation.LoginActivity
import org.junit.Rule

open class BaseEspressoTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)

    protected fun loginScreen(func: LoginScreenHelper.() -> Unit) = LoginScreenHelper().apply { func() }
    protected fun newsScreen(func: NewsScreenHelper.() -> Unit) = NewsScreenHelper().apply { func() }
}