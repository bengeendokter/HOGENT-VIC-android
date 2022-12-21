package be.hogent.vic.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import be.hogent.vic.database.VicDatabase
import be.hogent.vic.database.VirtualMachineRequestDatabaseDto
import be.hogent.vic.database.asDomainModel
import be.hogent.vic.network.Network
import be.hogent.vic.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VirtualMachineRequestRepository(private val database: VicDatabase) {
    private val _virtualMachineRequest = MutableLiveData<VirtualMachineRequestDatabaseDto?>()

//    val virtualMachineRequests: LiveData<List<VirtualMachineRequest>> =
//        Transformations.map(database.virtualMachineRequestDao.getVirtualMachineRequests()){
//            it.asDomainModel()
//        }
    val virtualMachineRequest = Transformations.map(_virtualMachineRequest) {
        it?.asDomainModel()
    }

    val virtualMachineRequests = Transformations.map(database.virtualMachineRequestDao.getAll()) {
        it.asDomainModel()
    }

    suspend fun refreshVirtualMachineRequests() {
        withContext(Dispatchers.IO) {
            val virtualMachineRequests = Network.vic.getVirtualMachineRequests()
            database.virtualMachineRequestDao.insertAll(* virtualMachineRequests.asDatabaseModel())
        }
    }

    suspend fun fetchVirtualMachineRequest(id: Int) {
        var value: VirtualMachineRequestDatabaseDto?

        withContext(Dispatchers.IO) {
            val virtualMachineRequest = Network.vic.getVirtualMachineRequest(id)
            database.virtualMachineRequestDao.update(virtualMachineRequest.asDatabaseModel())
            value = database.virtualMachineRequestDao.getById(id)
        }

        _virtualMachineRequest.value = value
    }
}
