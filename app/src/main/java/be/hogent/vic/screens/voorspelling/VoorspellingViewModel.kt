package be.hogent.vic.screens.voorspelling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hogent.vic.domain.VirtualMachine
import be.hogent.vic.domain.Voorspelling
import java.text.SimpleDateFormat
import java.util.*

class VoorspellingViewModel: ViewModel() {
    private val vms = listOf(
        VirtualMachine(
            1, "VM-IT-1", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            4, 3, 950, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/01"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/02")
        ),
        VirtualMachine(
            2, "VM-IT-2", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            2, 5, 920, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/01"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/02")
        ),
        VirtualMachine(
            3, "VM-IT-3", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            4, 3, 950, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/03"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/06")
        ),
        VirtualMachine(
            4, "VM-IT-4", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            2, 5, 920, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/20"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/21")
        ),
        VirtualMachine(
            5, "VM-IT-5", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            4, 3, 950, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/22"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/23")
        ),
        VirtualMachine(
            6, "VM-IT-6", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            2, 5, 920, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/24"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/25")
        )

    )

    private var _voorspelling = MutableLiveData<Voorspelling>()
    val voorspelling: LiveData<Voorspelling>
        get() = _voorspelling

    init {
        _voorspelling.value = Voorspelling( Array(3) { "" }, Array(3) { "" }, Array(3) { "" })
    }

    fun doeVoorspelling(datum: Date): Unit{
        val tot = berekenVoorspelling(datum, true)
        val vrij = berekenVoorspelling(datum, false)

        _voorspelling.value =  Voorspelling(
            tot.map { it.toString() }.toTypedArray(),
            vrij.map { it.toString() }.toTypedArray(),
            tot.zip(vrij).map { (x1, x2)->
                x1 - x2
            }.map { it.toString() }.toTypedArray()
        );
    }

    fun berekenVoorspelling(datum: Date, totaal: Boolean): IntArray {
        var lijst = IntArray(3) { 0 }

        var vmlijst = vms
        if (totaal) {
            vmlijst = vms.filter{ it.startDate <= datum }.toList()
        } else {
            vmlijst = vms.filter { it.startDate <= datum && it.endDate <= datum }.toList()
        }

        vmlijst.forEach{ v ->
            var tempCPU: Int = lijst[0]
            var tempRAM: Int = lijst[1]
            var tempStorage: Int = lijst[2]

            lijst[0] = tempCPU + v.cpu;
            lijst[1] = tempRAM + v.ram;
            lijst[2] = tempStorage + v.storage;
        }
        return lijst
    }
}