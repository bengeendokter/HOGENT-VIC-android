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

    private var _totaalRAM = MutableLiveData<String>()
    val totaalRAM: LiveData<String>
        get() = _totaalRAM

    private var _vrijRAM = MutableLiveData<String>()
    val vrijRAM: LiveData<String>
        get() = _vrijRAM

    private var _gebruikRAM = MutableLiveData<String>()
    val gebruikRAM: LiveData<String>
        get() = _gebruikRAM

    private var _totaalStorage = MutableLiveData<String>()
    val totaalStorage: LiveData<String>
        get() = _totaalStorage

    private var _vrijStorage = MutableLiveData<String>()
    val vrijStorage: LiveData<String>
        get() = _vrijStorage

    private var _gebruikStorage = MutableLiveData<String>()
    val gebruikStorage: LiveData<String>
        get() = _gebruikStorage

    init {
        _totaalCPU.value = "0"
        _vrijCPU.value = "0"
        _gebruikCPU.value = "0"

        _totaalRAM.value = "0"
        _vrijRAM.value = "0"
        _gebruikRAM.value = "0"

        _totaalStorage.value = "0"
        _vrijStorage.value = "0"
        _gebruikStorage.value = "0"
    }

    fun geefcpu(datum: Date): Unit{
        _totaalCPU.value = definitieftotaalCPUfunc(datum).toString()
        _vrijCPU.value = definitiefvrijCPUfunc(datum).toString()
        _gebruikCPU.value = definitiefgebruikCPUfunc(datum).toString()

        _totaalRAM.value = definitieftotaalRAMfunc(datum).toString()
        _vrijRAM.value = definitiefvrijRAMfunc(datum).toString()
        _gebruikRAM.value = definitiefgebruikRAMfunc(datum).toString()

        _totaalStorage.value = definitieftotaalStoragefunc(datum).toString()
        _vrijStorage.value = definitiefvrijStoragefunc(datum).toString()
        _gebruikStorage.value = definitiefgebruikStoragefunc(datum).toString()
    }

    fun definitieftotaalCPUfunc(datum: Date): Int { return totaalVMCPU(datum); }
    fun definitiefvrijCPUfunc(datum: Date): Int { return vrijVMS(datum)[0]; }
    fun definitiefgebruikCPUfunc(datum: Date): Int { return (totaalVMCPU(datum) - vrijVMS(datum)[0]); }

    fun definitieftotaalRAMfunc(datum: Date): Int { return totaalVMRAM(datum); }
    fun definitiefvrijRAMfunc(datum: Date): Int { return vrijVMS(datum)[1]; }
    fun definitiefgebruikRAMfunc(datum: Date): Int { return (totaalVMRAM(datum) - vrijVMS(datum)[1]); }

    fun definitieftotaalStoragefunc(datum: Date): Int { return totaalVMStorage(datum);}
    fun definitiefvrijStoragefunc(datum: Date): Int { return vrijVMS(datum)[2]; }
    fun definitiefgebruikStoragefunc(datum: Date): Int { return (totaalVMStorage(datum) - vrijVMS(datum)[2]); }


    //totaal
    fun totaalVMCPU(datum: Date): Int { return vms.filter{ it.startDate <= datum }.fold(0){ acc, v -> acc + v.cpu}}
    fun totaalVMRAM(datum: Date): Int { return vms.filter{ it.startDate <= datum }.fold(0){ acc, v -> acc + v.ram}}
    fun totaalVMStorage(datum: Date): Int { return vms.filter{ it.startDate <= datum }.fold(0){ acc, v -> acc + v.storage}}

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