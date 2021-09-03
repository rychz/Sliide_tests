package com.test.news.tests

import com.test.news.BaseEspressoTest
import com.test.news.testHelpers.disableOfflineMode
import com.test.news.testHelpers.enableOfflineMode
import com.test.news.utils.TestedScenario
import org.junit.Test


class NewsTest : BaseEspressoTest() {

    /**
     * Espresso tests that covers user story: As a user I want to see my news
     */

    @TestedScenario("news images are loaded")
    @Test
    fun checkImagesWereLoaded() {
        inLoginScreen {
            fillUserName()
            fillPassword()
            clickLoginButton()
        }
        inNewsScreen {
            assertMainRecyclerViewIsDisplayed()
            assertNewsAreLoaded()
        }
    }

    @TestedScenario("news image is clicked")
    @Test
    fun checkWebBrowserOpensOnClick() {
        val newsRow = 0
        val newsPos = 2
        inLoginScreen {
            fillUserName()
            fillPassword()
            clickLoginButton()
        }
        inNewsScreen {
            assertMainRecyclerViewIsDisplayed()
            openNewsAndValidateBrowserIntent(newsRow, newsPos)
        }
    }

    @TestedScenario("failed to load images")
    @Test
    fun checkErrorWhileOffline() {
        try {
            enableOfflineMode()
            inLoginScreen {
                fillUserName()
                fillPassword()
                clickLoginButton()
            }
            inNewsScreen {
                assertNewsScreenIsOpen()
                assertFailedToLoadNewsError()
            }
        } finally {
            disableOfflineMode()

        }
    }

}