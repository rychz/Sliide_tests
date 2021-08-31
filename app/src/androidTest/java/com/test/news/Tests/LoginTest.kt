package com.test.news.Tests

import com.test.news.*
import com.test.news.Utils.*
import org.junit.Test

class LoginTest : BaseEspressoTest() {

    /**
     * Espresso tests that covers user story: As a user I want to log in to the app
     */

    @TestedScenarioAnnotation("user opens the android app first time (when not logged in yet)")
    @Test
    fun firstTimeAppOpen() {
        loginScreen {
            verifyUi()
        }
    }

    @TestedScenarioAnnotation("the user provided wrong user name")
    @Test
    fun tryLoginWithIncorrectUsername() {
        loginScreen {
            fillUserName(INCORRECT_USERNAME)
            fillPassword()
            clickLoginButton()

            assertIncorrectUsernameErrorMarker()
        }
    }

    @TestedScenarioAnnotation("the user provided wrong password")
    @Test
    fun tryLoginWithIncorrectPassword() {
        loginScreen {
            fillUserName()
            fillPassword(INCORRECT_PASSWORD)
            clickLoginButton()

            assertIncorrectPasswordErrorMarker()
        }
    }

    @TestedScenarioAnnotation("the user provided wrong user name and password")
    @Test
    fun tryLoginWithIncorrectCredentials() {
        loginScreen {
            fillUserName(INCORRECT_USERNAME)
            fillPassword(INCORRECT_PASSWORD)
            clickLoginButton()

            assertIncorrectCredentialsErrorMarkers()
        }
    }

    @TestedScenarioAnnotation("user login succeed")
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






}