package com.backbase.assignment.presentation.ui.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import com.backbase.assignment.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.anything
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenIntegrationTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    /**
     * TODO Come back to fix this - see note
     * There's currently some problems with hilt and fragment container due to the annotation management
     * there are workaround but I leave this note to comeback later to review it.
     */
    @Test
    fun testNavigateToDetailMovieFragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.app_nav_graph)

        val detailScenario = launchFragmentInContainer<HomeFragment>()
        detailScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
        onData(anything()).inAdapterView(ViewMatchers.withId(R.id.popularMovieList)).atPosition(0).perform(click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.movieDetailFragment)
    }
}