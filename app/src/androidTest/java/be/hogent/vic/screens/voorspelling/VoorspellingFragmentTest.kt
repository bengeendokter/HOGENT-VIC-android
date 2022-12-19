package be.hogent.vic.screens.voorspelling

import android.graphics.Color.RED
import android.hardware.camera2.params.RggbChannelVector.RED
import be.hogent.vic.screens.voorspelling.VoorspellingFragment
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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import be.hogent.vic.R
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.not
import org.junit.Assert

@RunWith(AndroidJUnit4::class)
class VoorspellingFragmentTest {
    //val scenario = launchFragmentInContainer<HomeFragment>()

    //onView(withText("Cancel")).check(doesNotExist())

    @Before
    fun setup() {
        //scenario = launchFragmentInContainer(themeResId = R.style.Theme_SpendTracker)
        launchFragmentInContainer<VoorspellingFragment>()
        //scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun algemeenUI() {
        //Espresso Matcher and Action
        //onView(withId(R.id.edit_text_amount)).perform(typeText(amount.toString()))
        //onView(withId(R.id.edit_text_description)).perform(typeText(desc))
        //Espresso.closeSoftKeyboard()
        //onView(withId(R.id.button_add)).perform(click())

        //Assertion
        //onView(withId(R.id.text_view_success_message)).check(matches(withText("Spend Added")))
        onView(withId(R.id.voorspelling_datum)).check(matches(isDisplayed()))
        onView(withId(R.id.voorspelling_datum)).check(matches(hasTextColor(R.color.black)))
        onView(withId(R.id.voorspelling_btn_datum)).check(matches(isDisplayed()))
        onView(withId(R.id.voorspelling_btn_datum)).check(matches(isClickable()))
        onView(withId(R.id.voorspelling_btn_datum)).check(matches(withText(containsString("Kies"))))
        onView(withId(R.id.voorspelling_btn_datum)).check(matches(withText("Kies een datum")))

        //TODO
        // datapicker test en kijken naar datum text en cpu velden
    }
}