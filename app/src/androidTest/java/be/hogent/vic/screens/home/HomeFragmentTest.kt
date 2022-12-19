package be.hogent.vic.screens.home

/*import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider*/
import android.os.Build.VERSION_CODES.Q
import androidx.fragment.app.testing.launchFragmentInContainer
//import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import be.hogent.vic.R
import org.junit.Assert

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    //val scenario = launchFragmentInContainer<HomeFragment>()

    //onView(withText("Cancel")).check(doesNotExist())

    @Before
    fun setup() {
        //scenario = launchFragmentInContainer(themeResId = R.style.Theme_SpendTracker)
        launchFragmentInContainer<HomeFragment>()
        //scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun specifiekUIButtonVMS() {
        //Espresso Matcher and Action
        //onView(withId(R.id.edit_text_amount)).perform(typeText(amount.toString()))
        //onView(withId(R.id.edit_text_description)).perform(typeText(desc))
        //Espresso.closeSoftKeyboard()
        //onView(withId(R.id.button_add)).perform(click())

        //Assertion
        //onView(withId(R.id.text_view_success_message)).check(matches(withText("Spend Added")))
        //Assert.assertEquals(2, 1 + 1)

        //onView(withId(R.id.hogent_logo)).check(matches(isDisplayed()))

        onView(withId(R.id.nav_btn_virtualMachines)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_btn_virtualMachines)).check(matches(isClickable()))
        onView(withId(R.id.nav_btn_virtualMachines)).check(matches(withText(containsString("Virtual"))))
        onView(withId(R.id.nav_btn_virtualMachines)).check(matches(withText("Virtual Machines")))

        //TODO
        //op switch klikken en kijken naar kleurverandering
    }
}