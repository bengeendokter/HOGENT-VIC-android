package be.hogent.vic.screens.virtualmachinerequest

import android.app.Application
import androidx.lifecycle.*
import be.hogent.vic.database.getDatabase
import be.hogent.vic.domain.VirtualMachineRequest
import be.hogent.vic.repository.VirtualMachineRequestRepository
import kotlinx.coroutines.launch

class VirtualMachineRequestViewModel(private val requestId: Int, application: Application): AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val virtualMachineRequestRepository = VirtualMachineRequestRepository(database)
    val virtualMachineRequest = virtualMachineRequestRepository.virtualMachineRequest

    init {
        viewModelScope.launch {
            virtualMachineRequestRepository.fetchVirtualMachineRequest(requestId)
        }
    }


    class VirtualMachineRequestFactory(private val requestId: Int, private val application: Application): ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(VirtualMachineRequestViewModel::class.java)){
                return VirtualMachineRequestViewModel(requestId, application) as T
            }
            throw IllegalArgumentException("unknown viewmodel class")
        }
    }
}