package be.hogent.vic.screens.voorspelling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hogent.vic.domain.VirtualMachine
import java.text.SimpleDateFormat
import java.util.*

class VoorspellingViewModel: ViewModel() {
    var cpu = 0

    private val vms = listOf(
        VirtualMachine(
            1, "VM-IT-1", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            4, 3, 950, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/24"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/25")
        ),
        VirtualMachine(
            2, "VM-IT-2", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            2, 5, 920, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/26"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/27")
        ),
        VirtualMachine(
            3, "VM-IT-3", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            4, 3, 950, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/28"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/29")
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
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/22"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/12/23")
        )

    )

    private val _aantalCPU = MutableLiveData<Int>()
    val aantalCPU: LiveData<Int>
        get() = _aantalCPU

    fun geefcpu(datum: Date): String{
        var cal: Calendar = Calendar.getInstance()
        cal.setTimeInMillis(datum.getTime())
        //var num = (cal.get(Calendar.DAY_OF_MONTH))
        //return num.toString()
        var num2 = totaalVMCPU(datum)
        var num3 = num2
        return num3.toString()
    }


    //totaal
    fun totaalVMCPU(datum: Date): Int { return vms.filter{ it.startDate <= datum }.fold(0){ acc, v -> acc + v.cpu}}
    //return vms.filter(v => (v.StartDate <= datum)).ToList().Aggregate(0, (acc, v) => acc + v.CPU);
    /*private int TotaalVMRAM(DateTime datum) { return _vms.Where(v => (v.StartDate <= datum)).ToList().Aggregate(0, (acc, v) => acc + v.RAM); }
    private int TotaalVMStorage(DateTime datum) { return _vms.Where(v => (v.StartDate <= datum)).ToList().Aggregate(0, (acc, v) => acc + v.Storage); }*/
}