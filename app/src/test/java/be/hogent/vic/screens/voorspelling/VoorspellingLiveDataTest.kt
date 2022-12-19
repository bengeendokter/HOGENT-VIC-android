package be.hogent.vic.screens.voorspelling

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
//import androidx.test.core.app.ApplicationProvider
import be.hogent.vic.domain.BackupFrequency
import be.hogent.vic.domain.Template
import be.hogent.vic.domain.VirtualMachine
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import be.hogent.vic.domain.Voorspelling
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertSame
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat

@RunWith(AndroidJUnit4::class)
class  VoorspellingLiveDataTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var context: Context
    private lateinit var voorspellingViewModel: VoorspellingViewModel
    private val lijstvms = listOf(
        VirtualMachine(
            1, "VM-IT-1",
            4, 3, 950,
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/01"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/02"),
            true, true, Template.DOCKER_LINUX, BackupFrequency.WEEKLY
        ),
        VirtualMachine(
            2, "VM-IT-2",
            2, 5, 920,
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/01"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/02"),
            true, true, Template.DOCKER_LINUX, BackupFrequency.WEEKLY
        ),
        VirtualMachine(
            3, "VM-IT-3",
            4, 3, 950,
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/03"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/06"),
            true, true, Template.DOCKER_LINUX, BackupFrequency.WEEKLY
        ),
        VirtualMachine(
            4, "VM-IT-4",
            2, 5, 920,
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/20"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/21"),
            true, true, Template.DOCKER_LINUX, BackupFrequency.WEEKLY
        ),
        VirtualMachine(
            5, "VM-IT-5",
            4, 3, 950,
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/22"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/23"),
            true, true, Template.DOCKER_LINUX, BackupFrequency.WEEKLY
        ),
        VirtualMachine(
            6, "VM-IT-6",
            2, 5, 920,
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/24"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/25"),
            true, true, Template.DOCKER_LINUX, BackupFrequency.WEEKLY
        )

    )

    @Before
    fun setUp() {
        //context = ApplicationProvider.getApplicationContext()
        context = InstrumentationRegistry.getInstrumentation().targetContext
        voorspellingViewModel = VoorspellingViewModel(context as Application)
    }

    @Test
    fun doeResourcesBerekening_metLegeLijst_geeftLiveDataMetNulArray(){
        voorspellingViewModel.vms = MutableLiveData<List<VirtualMachine>>(listOf())

        //vs1
        var voorspelling = Voorspelling(
            arrayOf<String>("0", "0", "0"),
            arrayOf<String>("0", "0", "0"),
        );

        var value = voorspellingViewModel.voorspelling.getOrAwaitValue()
        assertThat(value, not(nullValue()))
        assertEquals(
            value.vrij.joinToString(),
            voorspelling.vrij.joinToString()
        )
        //Assert.assertArrayEquals(value.vrij, voorspelling.vrij)

        // Then the percentages are 100 and 0
        //Assert.assertArrayEquals(result, intArrayOf(10, 11, 2820))
        //Assert.assertArrayEquals(result, IntArray(3) {0})
        //Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun doeResourcesBerekening_metVMLijst_geeftLiveDataMetJuisteArray(){
        voorspellingViewModel.vms = MutableLiveData<List<VirtualMachine>>(lijstvms)

        val result = voorspellingViewModel.doeVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/19")
        )

        //vs2
        var voorspelling = Voorspelling(
            arrayOf<String>("10", "11", "2820"),
            arrayOf<String>("10", "11", "2820")
        );


        var value = voorspellingViewModel.voorspelling.getOrAwaitValue()
        assertThat(value, not(nullValue()))
        assertEquals(
            value.vrij.joinToString(),
            voorspelling.vrij.joinToString()
        )
        //Assert.assertArrayEquals(value.vrij, voorspelling.vrij)
    }
}