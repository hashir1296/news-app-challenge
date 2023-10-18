package com.example.criticaltechworks_newsapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/*This rules helps us provide a test dispatcher, instead of writing dispatchers
* everytime for each test case, we are defining a rule to setup an environment for the test cases that
* use dispatchers*/
@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    //Starting function is just like @Before in test cases, it is launched before executing test case
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    //Finished function is just like @After in test cases, it is launched after test case is executed
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}