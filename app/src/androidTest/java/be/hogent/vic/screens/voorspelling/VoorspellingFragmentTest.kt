package be.hogent.vic.screens.voorspelling

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import be.hogent.vic.R
import org.hamcrest.CoreMatchers.containsString

@RunWith(AndroidJUnit4::class)
class VoorspellingFragmentTest {

    @Before
    fun setup() {
        launchFragmentInContainer<VoorspellingFragment>()
    }

    @Test
    fun algemeenUI() {
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