package be.hogent.vic.screens.voorspelling

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import be.hogent.vic.domain.BackupFrequency
import be.hogent.vic.domain.Template
import be.hogent.vic.domain.VirtualMachine
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat

@RunWith(AndroidJUnit4::class)
class VoorspellingViewModelTest {
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
        )
    )

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        voorspellingViewModel = VoorspellingViewModel(context as Application)
    }

    @Test
    fun berekenResources_metLegeLijst_willekeurigeDatum() {
        voorspellingViewModel.vms = MutableLiveData<List<VirtualMachine>>(listOf())

        val totaal = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/19"),
            true
        )

        val vrij = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/19"),
            false
        )

        Assert.assertArrayEquals(totaal, IntArray(3) { 0 })
        Assert.assertArrayEquals(vrij, IntArray(3) { 0 })
    }

    @Test
    fun berekenResources_metVMLijst_datumVoorAlleStartDatums() {
        voorspellingViewModel.vms = MutableLiveData<List<VirtualMachine>>(lijstvms)

        val totaal = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/10/10"),
            true
        )

        val vrij = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/10/10"),
            false
        )

        Assert.assertArrayEquals(totaal, IntArray(3) { 0 })
        Assert.assertArrayEquals(vrij, IntArray(3) { 0 })
    }

    @Test
    fun berekenResources_metVMLijst_datumNaEenStartDatum() {
        voorspellingViewModel.vms = MutableLiveData<List<VirtualMachine>>(lijstvms)

        val totaal = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/01"),
            true
        )

        val vrij = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/01"),
            false
        )

        Assert.assertArrayEquals(totaal, intArrayOf(4, 3, 950))
        Assert.assertArrayEquals(vrij, IntArray(3) { 0 })
    }

    @Test
    fun berekenResources_metVMLijst_datumNaEenEinddatum() {
        voorspellingViewModel.vms = MutableLiveData<List<VirtualMachine>>(lijstvms)

        val totaal = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/02"),
            true
        )

        val vrij = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/02"),
            false
        )

        Assert.assertArrayEquals(totaal, intArrayOf(4, 3, 950))
        Assert.assertArrayEquals(vrij, intArrayOf(4, 3, 950))
    }

    @Test
    fun berekenResources_metVMLijst_datumNaLaatsteStartdatum() {
        voorspellingViewModel.vms = MutableLiveData<List<VirtualMachine>>(lijstvms)

        val totaal = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/04"),
            true
        )

        val vrij = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/04"),
            false
        )

        Assert.assertArrayEquals(totaal, intArrayOf(10, 11, 2820))
        Assert.assertArrayEquals(vrij, intArrayOf(6, 8, 1870))
    }

    @Test
    fun berekenResources_metVMLijst_datumNaAlleEinddatums() {
        voorspellingViewModel.vms = MutableLiveData<List<VirtualMachine>>(lijstvms)

        val totaal = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/07"),
            true
        )

        val vrij = voorspellingViewModel.berekenVoorspelling(
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/07"),
            false
        )

        Assert.assertArrayEquals(totaal, intArrayOf(10, 11, 2820))
        Assert.assertArrayEquals(vrij, intArrayOf(10, 11, 2820))
    }
}
