package be.hogent.vic.screens.virtualmachine

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import be.hogent.vic.database.getDatabase
import be.hogent.vic.repository.VirtualMachineRepository
import kotlinx.coroutines.launch

class VirtualMachineViewModel(application: Application, val vmId: Int) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val virtualMachineRepository = VirtualMachineRepository(database)
    val virtualMachine = virtualMachineRepository.virtualMachine

    init {
        viewModelScope.launch {
            virtualMachineRepository.fetchVirtualMachine(vmId)
        }
    }

    class Factory(val app: Application, val vmId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VirtualMachineViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VirtualMachineViewModel(app, vmId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}