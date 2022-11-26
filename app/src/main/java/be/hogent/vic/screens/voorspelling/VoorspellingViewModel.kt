package be.hogent.vic.screens.voorspelling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hogent.vic.domain.VirtualMachine
import java.text.SimpleDateFormat
import java.util.*

class VoorspellingViewModel: ViewModel() {
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

    private var _totaalCPU = MutableLiveData<String>()
    val totaalCPU: LiveData<String>
        get() = _totaalCPU

    private var _vrijCPU = MutableLiveData<String>()
    val vrijCPU: LiveData<String>
        get() = _vrijCPU

    private var _gebruikCPU = MutableLiveData<String>()
    val gebruikCPU: LiveData<String>
        get() = _gebruikCPU

    init {
        _totaalCPU.value = "0"
        _vrijCPU.value = "0"
        _gebruikCPU.value = "0"
    }

    fun geefcpu(datum: Date): Unit{
        var cal: Calendar = Calendar.getInstance()
        cal.setTimeInMillis(datum.getTime())
        //var num = (cal.get(Calendar.DAY_OF_MONTH))
        //return num.toString()
        var num2 = totaalVMCPU(datum)
        /*var num3 = num2
        return num3.toString()*/
        _totaalCPU.value = definitieftotaalCPUfunc(datum).toString() //"0"
        _vrijCPU.value = definitiefvrijCPUfunc(datum).toString()  //"0"
        _gebruikCPU.value = definitiefgebruikCPUfunc(datum).toString() //"0"
    }

    fun definitieftotaalCPUfunc(datum: Date): Int { return totaalVMCPU(datum); }
    fun definitiefvrijCPUfunc(datum: Date): Int { return vrijVMS(datum)[0]; }
    fun definitiefgebruikCPUfunc(datum: Date): Int { return (totaalVMCPU(datum) - vrijVMS(datum)[0]); }


    //totaal
    fun totaalVMCPU(datum: Date): Int { return vms.filter{ it.startDate <= datum }.fold(0){ acc, v -> acc + v.cpu}}
    //return vms.filter(v => (v.StartDate <= datum)).ToList().Aggregate(0, (acc, v) => acc + v.CPU);
    /*private int TotaalVMRAM(DateTime datum) { return _vms.Where(v => (v.StartDate <= datum)).ToList().Aggregate(0, (acc, v) => acc + v.RAM); }
    private int TotaalVMStorage(DateTime datum) { return _vms.Where(v => (v.StartDate <= datum)).ToList().Aggregate(0, (acc, v) => acc + v.Storage); }*/

    fun vrijVMS(datum: Date): IntArray
    {
        var lijst = IntArray(3) { 0 }
        vms.filter {v -> (v.startDate <= datum && v.endDate <= datum) }.forEach{ v ->
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