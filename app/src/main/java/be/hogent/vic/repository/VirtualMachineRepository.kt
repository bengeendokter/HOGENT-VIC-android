package be.hogent.vic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import be.hogent.vic.database.VicDatabase
import be.hogent.vic.database.asDomainModel
import be.hogent.vic.domain.VirtualMachine
import be.hogent.vic.network.Network
import be.hogent.vic.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VirtualMachineRepository(private val database: VicDatabase) {
    val virtualMachines: LiveData<List<VirtualMachine>> =
        Transformations.map(database.virtualMachineDao.getVirtualMachines()) {
            it.asDomainModel()
        }

    suspend fun refreshVirtualMachines() {
        withContext(Dispatchers.IO) {
            val virtualMachines = Network.vic.getVirtualMachines().await()
            database.virtualMachineDao.insertAll(*virtualMachines.asDatabaseModel())
        }
    }
}