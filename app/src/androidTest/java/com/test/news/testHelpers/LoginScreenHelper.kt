package com.test.news.testHelpers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.test.news.R
import com.test.news.utils.*

class LoginScreenHelper {

    private val userNameTextField = onView(withId(R.id.editTextUserName))
    private val passwordTextField = onView(withId(R.id.editTextPassword))
    private val loginButton = onView(withId(R.id.buttonLogin))

    fun fillUserName(userName: String = DEFAULT_USERNAME) {
        userNameTextField.typeText(userName)
    }

    fun fillPassword(password: String = DEFAULT_PASSWORD) {
        passwordTextField.typeText(password)
    }

    fun clickLoginButton() {
        loginButton.click()
    }

    fun verifyUi() {
        userNameTextField.isDisplayed()
        passwordTextField.isDisplayed()
        loginButton.isDisplayed()
    }

    fun assertIncorrectUsernameErrorMarker() {
        val expectedError = appContext.getString(R.string.login_wrong_user_name_error)
        userNameTextField.check(matches(hasErrorText(expectedError)))
    }

    fun assertIncorrectPasswordErrorMarker() {
        val expectedError = appContext.getString(R.string.login_wrong_password_error)
        passwordTextField.check(matches(hasErrorText(expectedError)))
    }

    fun assertIncorrectCredentialsErrorMarkers() {
        assertIncorrectUsernameErrorMarker()
        assertIncorrectPasswordErrorMarker()
    }

}