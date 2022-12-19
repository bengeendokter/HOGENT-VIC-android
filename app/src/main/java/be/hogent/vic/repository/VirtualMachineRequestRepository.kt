package be.hogent.vic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import be.hogent.vic.database.VicDatabase
import be.hogent.vic.database.asDomainModel
import be.hogent.vic.domain.VirtualMachineRequest
import be.hogent.vic.network.Network
import be.hogent.vic.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VirtualMachineRequestRepository(private val database: VicDatabase) {
    val virtualMachineRequests: LiveData<List<VirtualMachineRequest>> =
        Transformations.map(database.virtualMachineRequestDao.getVirtualMachineRequests()){
            it.asDomainModel()
        }

    suspend fun refreshVirtualMachineRequests(){
        withContext(Dispatchers.IO){
            val virtualMachineRequests = Network.vic.getVirtualMachineRequests()
            database.virtualMachineRequestDao.insertAll(* virtualMachineRequests.asDatabaseModel())
        }
    }
}