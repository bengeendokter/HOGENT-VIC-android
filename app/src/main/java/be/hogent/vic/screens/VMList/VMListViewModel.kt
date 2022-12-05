package be.hogent.vic.screens.VMList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import be.hogent.vic.database.getDatabase
import be.hogent.vic.repository.VirtualMachineRepository
import kotlinx.coroutines.launch

class VMListViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val virtualMachineRepository = VirtualMachineRepository(database)
    val virtualMachines = virtualMachineRepository.virtualMachines

    init {
        viewModelScope.launch {
            virtualMachineRepository.refreshVirtualMachines()
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VMListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VMListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}