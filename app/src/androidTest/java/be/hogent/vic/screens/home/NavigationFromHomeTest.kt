package be.hogent.vic.screens.home

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import be.hogent.vic.R
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationFromHomeTest {
    private lateinit var navController: TestNavHostController
    private lateinit var testScenario: FragmentScenario<HomeFragment>

    @Before
    fun setup() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        testScenario = launchFragmentInContainer<HomeFragment>()
        testScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.navigation)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun testNavigationHomeToVMList() {
        onView(ViewMatchers.withId(R.id.nav_btn_virtualMachines)).perform(ViewActions.click())
        assertEquals(navController.currentDestination?.id, R.id.VMListFragment)
    }

    @Test
    fun testNavigationHomeToRequestList() {
        onView(ViewMatchers.withId(R.id.nav_btn_aanvragen)).perform(ViewActions.click())
        assertEquals(navController.currentDestination?.id, R.id.virtualMachineRequestListFragment)
    }

    @Test
    fun testNavigationHomeToClientList() {
        onView(ViewMatchers.withId(R.id.nav_btn_klanten)).perform(ViewActions.click())
        assertEquals(navController.currentDestination?.id, R.id.clientListFragment)
    }

    @Test
    fun testNavigationHomeToVoorspelling() {
        onView(ViewMatchers.withId(R.id.nav_btn_voorspelling)).perform(ViewActions.click())
        assertEquals(navController.currentDestination?.id, R.id.voorspellingFragment)
    }
}
