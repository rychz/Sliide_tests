package com.test.news

import com.test.news.tests.LoginTest
import com.test.news.tests.NewsTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginTest::class,
    NewsTest::class
)
class TestSuite
