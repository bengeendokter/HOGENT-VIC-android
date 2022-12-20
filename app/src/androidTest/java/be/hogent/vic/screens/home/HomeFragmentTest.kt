package be.hogent.vic.screens.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import be.hogent.vic.R

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Before
    fun setup() {
        launchFragmentInContainer<HomeFragment>()
    }

    @Test
    fun specifiekUIButtonVMS() {
        onView(withId(R.id.nav_btn_virtualMachines)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_btn_virtualMachines)).check(matches(isClickable()))
        onView(withId(R.id.nav_btn_virtualMachines)).check(matches(withText(containsString("Virtual"))))
        onView(withId(R.id.nav_btn_virtualMachines)).check(matches(withText("Virtual Machines")))

        //TODO
        //op switch klikken en kijken naar kleurverandering
    }

    @Test
    fun specifiekUIButtonAanvragen() {
        onView(withId(R.id.nav_btn_aanvragen)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_btn_aanvragen)).check(matches(isClickable()))
        onView(withId(R.id.nav_btn_aanvragen)).check(matches(withText(containsString("Aanvragen"))))
        onView(withId(R.id.nav_btn_aanvragen)).check(matches(withText("Aanvragen")))
    }

    @Test
    fun specifiekUIButtonKlanten() {
        onView(withId(R.id.nav_btn_klanten)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_btn_klanten)).check(matches(isClickable()))
        onView(withId(R.id.nav_btn_klanten)).check(matches(withText(containsString("Klanten"))))
        onView(withId(R.id.nav_btn_klanten)).check(matches(withText("Klanten")))
    }

    @Test
    fun specifiekUIButtonResourcePlanner() {
        onView(withId(R.id.nav_btn_voorspelling)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_btn_voorspelling)).check(matches(isClickable()))
        onView(withId(R.id.nav_btn_voorspelling)).check(matches(withText(containsString("Resource"))))
        onView(withId(R.id.nav_btn_voorspelling)).check(matches(withText("Resource Planner")))
    }
}