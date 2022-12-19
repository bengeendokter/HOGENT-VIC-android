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

    @Test
    fun specifiekUICPU() {
        onView(withId(R.id.cardView)).check(matches(isDisplayed()))
        onView(withId(R.id.constraint1)).check(matches(isDisplayed()))

        onView(withId(R.id.iconCPU)).check(matches(isDisplayed()))
        onView(withId(R.id.imageCPU)).check(matches(isDisplayed()))

        onView(withId(R.id.infoCPU)).check(matches(isDisplayed()))
        onView(withId(R.id.textCPU)).check(matches(isDisplayed()))
        onView(withId(R.id.textCPU)).check(matches(withText(containsString("CPU"))))
        onView(withId(R.id.textCPU)).check(matches(withText("CPU")))
        onView(withId(R.id.textEenheidCPU)).check(matches(withText(containsString("vCPU"))))
        onView(withId(R.id.textEenheidCPU)).check(matches(withText("in vCPU")))

        onView(withId(R.id.dataCPU)).check(matches(isDisplayed()))
        onView(withId(R.id.resterend1)).check(matches(isDisplayed()))
        onView(withId(R.id.resterend1)).check(matches(hasTextColor(R.color.CTA)))
        onView(withId(R.id.vrijCPU)).check(matches(isDisplayed()))
        onView(withId(R.id.totaalCPU)).check(matches(isDisplayed()))
        onView(withId(R.id.totaalCPU)).check(matches(withText(containsString("van"))))
        /*onView(withId(R.id.voorspelling_btn_datum)).check(matches(withText(containsString("Americano"))))

        onView(withId(R.id.voorspelling_btn_datum)).check(matches(isDisplayed()))
        onView(withId(R.id.voorspelling_btn_datum)).check(matches(isClickable()))
        onView(withId(R.id.voorspelling_btn_datum)).check(matches(isNotClickable()))
        onView(withText("sdf")).check(matches(isNotClickable()))
        onView(withText("sdf")).check(matches(hasTextColor(R.color.purple_500)))
        onView(withId(R.id.voorspelling_btn_datum)).check(matches(hasTextColor(Color.BLACK)))*/
        //Assert.assertEquals(2, 1 + 1)
    }

    @Test
    fun specifiekUIRAM() {
        onView(withId(R.id.cardView2)).check(matches(isDisplayed()))
        onView(withId(R.id.constraint2)).check(matches(isDisplayed()))

        onView(withId(R.id.iconRAM)).check(matches(isDisplayed()))
        onView(withId(R.id.imageRAM)).check(matches(isDisplayed()))

        onView(withId(R.id.infoRAM)).check(matches(isDisplayed()))
        onView(withId(R.id.textRAM)).check(matches(isDisplayed()))
        onView(withId(R.id.textRAM)).check(matches(withText(containsString("RAM"))))
        onView(withId(R.id.textRAM)).check(matches(withText("RAM")))
        onView(withId(R.id.textEenheidRAM)).check(matches(withText(containsString("GB"))))
        onView(withId(R.id.textEenheidRAM)).check(matches(withText("in GB")))

        onView(withId(R.id.dataRAM)).check(matches(isDisplayed()))
        onView(withId(R.id.resterend2)).check(matches(isDisplayed()))
        onView(withId(R.id.resterend2)).check(matches(hasTextColor(R.color.CTA)))
        onView(withId(R.id.vrijRAM)).check(matches(isDisplayed()))
        onView(withId(R.id.totaalRAM)).check(matches(isDisplayed()))
        onView(withId(R.id.totaalRAM)).check(matches(withText(containsString("van"))))
    }

    @Test
    fun specifiekUIStorage() {
        onView(withId(R.id.cardView3)).check(matches(isDisplayed()))
        onView(withId(R.id.constraint3)).check(matches(isDisplayed()))

        onView(withId(R.id.iconStorage)).check(matches(isDisplayed()))
        onView(withId(R.id.imageStorage)).check(matches(isDisplayed()))

        onView(withId(R.id.infoStorage)).check(matches(isDisplayed()))
        onView(withId(R.id.textStorage)).check(matches(isDisplayed()))
        onView(withId(R.id.textStorage)).check(matches(withText(containsString("Opslag"))))
        onView(withId(R.id.textStorage)).check(matches(withText("Opslag")))
        onView(withId(R.id.textEenheidStorage)).check(matches(withText(containsString("GB"))))
        onView(withId(R.id.textEenheidStorage)).check(matches(withText("in GB")))

        onView(withId(R.id.dataStorage)).check(matches(isDisplayed()))
        onView(withId(R.id.resterend3)).check(matches(isDisplayed()))
        onView(withId(R.id.resterend3)).check(matches(hasTextColor(R.color.CTA)))
        onView(withId(R.id.vrijStorage)).check(matches(isDisplayed()))
        onView(withId(R.id.totaalStorage)).check(matches(isDisplayed()))
        onView(withId(R.id.totaalStorage)).check(matches(withText(containsString("van"))))
    }

}