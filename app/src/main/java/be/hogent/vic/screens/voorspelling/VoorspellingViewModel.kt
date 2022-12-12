package be.hogent.vic.screens.voorspelling

import android.app.Application
import androidx.lifecycle.*
import be.hogent.vic.database.getDatabase
import be.hogent.vic.domain.BackupFrequency
import be.hogent.vic.domain.Template
import be.hogent.vic.domain.VirtualMachine
import be.hogent.vic.domain.Voorspelling
import be.hogent.vic.repository.VirtualMachineRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class VoorspellingViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val virtualMachineRepository = VirtualMachineRepository(database)
    var vms =  virtualMachineRepository.virtualMachines

    private var _voorspelling = MutableLiveData<Voorspelling>()
    val voorspelling: LiveData<Voorspelling>
        get() = _voorspelling

    init {
        _voorspelling.value = Voorspelling( Array(3) { "0" }, Array(3) { "0" })
        viewModelScope.launch {
            virtualMachineRepository.refreshVirtualMachines()
            doeVoorspelling(Date())
        }
    }

    fun doeVoorspelling(datum: Date): Unit{
        val tot = berekenVoorspelling(datum, true)
        val vrij = berekenVoorspelling(datum, false)

        _voorspelling.value =  Voorspelling(
            tot.map { it.toString() }.toTypedArray(),
            vrij.map { it.toString() }.toTypedArray(),
        );
    }

    fun berekenVoorspelling(datum: Date, totaal: Boolean): IntArray {
        var lijst = IntArray(3) { 0 }

        var vmlijst: List<VirtualMachine>
        var vmlist = vms.value ?: listOf()

        if (totaal) {
            vmlijst = vmlist.filter{ it.startDate <= datum }.toList()
        } else {
            vmlijst = vmlist.filter { it.startDate <= datum && it.endDate <= datum }.toList()
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

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VoorspellingViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VoorspellingViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}