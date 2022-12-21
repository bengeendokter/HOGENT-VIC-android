package be.hogent.vic.screens.virtualmachinelist

import android.app.Application
import androidx.lifecycle.*
import be.hogent.vic.database.getDatabase
import be.hogent.vic.domain.VirtualMachine
import be.hogent.vic.repository.VirtualMachineRepository
import kotlinx.coroutines.launch

class VirtualMachineListViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val virtualMachineRepository = VirtualMachineRepository(database)
    val virtualMachines = virtualMachineRepository.virtualMachines

    private val _navigateToDetails = MutableLiveData<Int?>()
    val navigateToDetails: LiveData<Int?>
        get() = _navigateToDetails

    init {
        viewModelScope.launch {
            virtualMachineRepository.refreshVirtualMachines()
        }
    }

    fun displayDetails(vm: VirtualMachine) {
        _navigateToDetails.value = vm.id
    }

    fun displayDetailsComplete() {
        _navigateToDetails.value = null
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VirtualMachineListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VirtualMachineListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
