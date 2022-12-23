package be.hogent.vic.screens.virtualmachinerequestlist

import android.app.Application
import androidx.lifecycle.*
import be.hogent.vic.database.getDatabase
import be.hogent.vic.domain.VirtualMachineRequest
import be.hogent.vic.repository.VirtualMachineRequestRepository
import kotlinx.coroutines.launch

class VirtualMachineRequestListViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val virtualMachineRequestRepository = VirtualMachineRequestRepository(database)
    val virtualMachineRequests = virtualMachineRequestRepository.virtualMachineRequests

    private val _navigateToDetails = MutableLiveData<Int?>()
    val navigateToDetails: LiveData<Int?>
        get() = _navigateToDetails

    init {
        viewModelScope.launch {
            virtualMachineRequestRepository.refreshVirtualMachineRequests()
        }
    }

    fun displayDetails(request: VirtualMachineRequest) {
        _navigateToDetails.value = request.id
    }

    fun displayDetailsComplete() {
        _navigateToDetails.value = null
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VirtualMachineRequestListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VirtualMachineRequestListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
