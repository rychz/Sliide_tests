package com.test.news.testHelpers

import com.test.news.utils.uiAutomation

fun enableOfflineMode() {
    val disableWifiCommand = "svc wifi disable"
    val disableDataCommand = "svc data disable"
    sendAdbCommand(disableWifiCommand)
    sendAdbCommand(disableDataCommand)
}

fun disableOfflineMode() {
    val enableWifiCommand = "svc wifi enable"
    val enableDataCommand = "svc data enable"
    sendAdbCommand(enableWifiCommand)
    sendAdbCommand(enableDataCommand)
    // 2s delay end of test to make sure that internet
    // connection has been established for next test
    Thread.sleep(2000)
}

private fun sendAdbCommand(command: String) {
    uiAutomation.executeShellCommand(command)
}