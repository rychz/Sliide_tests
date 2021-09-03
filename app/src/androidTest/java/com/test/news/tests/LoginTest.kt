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
        inLoginScreen {
            verifyUi()
        }
    }

    @TestedScenario("the user provided wrong user name")
    @Test
    fun tryLoginWithIncorrectUsername() {
        inLoginScreen {
            fillUserName(INCORRECT_USERNAME)
            fillPassword()
            clickLoginButton()

            assertIncorrectUsernameErrorMarker()
        }
    }

    @TestedScenario("the user provided wrong password")
    @Test
    fun tryLoginWithIncorrectPassword() {
        inLoginScreen {
            fillUserName()
            fillPassword(INCORRECT_PASSWORD)
            clickLoginButton()

            assertIncorrectPasswordErrorMarker()
        }
    }

    @TestedScenario("the user provided wrong user name and password")
    @Test
    fun tryLoginWithIncorrectCredentials() {
        inLoginScreen {
            fillUserName(INCORRECT_USERNAME)
            fillPassword(INCORRECT_PASSWORD)
            clickLoginButton()

            assertIncorrectCredentialsErrorMarkers()
        }
    }

    @TestedScenario("user login succeed")
    @Test
    fun tryLoginWithCorrectCredentials() {
        inLoginScreen {
            fillUserName(CORRECT_USERNAME)
            fillPassword(CORRECT_PASSWORD)
            clickLoginButton()
        }
        inNewsScreen {
            assertNewsScreenIsOpen()
        }
    }

    @TestedScenario("user opens app next time (previously logged in)")
    @Test
    fun openAppWhenAlreadyLoggedIn() {
        inLoginScreen {
            fillUserName()
            fillPassword()
            clickLoginButton()
        }
        inNewsScreen {
            assertNewsScreenIsOpen()
        }
        closeCurrentActivity()
        startActivity()
        inNewsScreen {
            assertNewsScreenIsOpen()
        }
    }

}