package com.test.news.tests

import com.test.news.BaseEspressoTest
import com.test.news.utils.*
import org.junit.Test

class LoginTest : BaseEspressoTest() {

    /**
     * Espresso tests that covers user story: As a user I want to log in to the app
     */

    @TestedScenario("user opens the android app first time (when not logged in yet)")
    @Test
    fun firstTimeAppOpen() {
        loginScreen {
            verifyUi()
        }
    }

    @TestedScenario("the user provided wrong user name")
    @Test
    fun tryLoginWithIncorrectUsername() {
        loginScreen {
            fillUserName(INCORRECT_USERNAME)
            fillPassword()
            clickLoginButton()

            assertIncorrectUsernameErrorMarker()
        }
    }

    @TestedScenario("the user provided wrong password")
    @Test
    fun tryLoginWithIncorrectPassword() {
        loginScreen {
            fillUserName()
            fillPassword(INCORRECT_PASSWORD)
            clickLoginButton()

            assertIncorrectPasswordErrorMarker()
        }
    }

    @TestedScenario("the user provided wrong user name and password")
    @Test
    fun tryLoginWithIncorrectCredentials() {
        loginScreen {
            fillUserName(INCORRECT_USERNAME)
            fillPassword(INCORRECT_PASSWORD)
            clickLoginButton()

            assertIncorrectCredentialsErrorMarkers()
        }
    }

    @TestedScenario("user login succeed")
    @Test
    fun tryLoginWithCorrectCredentials() {
        loginScreen {
            fillUserName(CORRECT_USERNAME)
            fillPassword(CORRECT_PASSWORD)
            clickLoginButton()
        }
        newsScreen {
            assertNewsScreenIsOpen()
        }
    }

    @TestedScenario("user opens app next time (previously logged in)")
    @Test
    fun openAppWhenAlreadyLoggedIn() {
        loginScreen {
            fillUserName()
            fillPassword()
            clickLoginButton()
        }
        newsScreen {
            assertNewsScreenIsOpen()
        }
        closeCurrentActivity()
        startActivity()
        newsScreen {
            assertNewsScreenIsOpen()
        }
    }

}