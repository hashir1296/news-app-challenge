package com.example.criticaltechworks_newsapp.presentation.newsList

import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.criticaltechworks_newsapp.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NewsHeadlinesListFragmentTest {

    @JvmField
    @Rule
    var hiltRule = HiltAndroidRule(this)

    @JvmField
    @Rule
    val activityScenario = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        hiltRule.inject()
        val context = InstrumentationRegistry.getInstrumentation().targetContext


        // Initialize the Navigation Controller for your fragment
        val navController = TestNavHostController(context)
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Set the graph for your navigation host
            navController.setGraph(com.example.criticaltechworks_newsapp.R.navigation.nav_graph)
        }


        // Launch your fragment within a FragmentScenario
        val scenario = FragmentScenario.launchInContainer(
            NewsHeadlinesListFragment::class.java, null
        )

        // Set the NavController for your fragment
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        onView(ViewMatchers.withId(com.example.criticaltechworks_newsapp.R.id.rvHeadLines)) // scrollTo will fail the test if no item matches.
            .perform(
                RecyclerViewActions.scrollTo<NewsHeadlinesListAdapter.NewsItemViewHolder>(
                    hasDescendant(withText("not in the list"))
                )
            )
    }
}