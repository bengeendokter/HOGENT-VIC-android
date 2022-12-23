package be.hogent.vic.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import be.hogent.vic.database.VicDatabase
import be.hogent.vic.database.VirtualMachineDatabaseDto
import be.hogent.vic.database.asDomainModel
import be.hogent.vic.network.Network
import be.hogent.vic.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VirtualMachineRepository(private val database: VicDatabase) {
    private val _virtualMachine = MutableLiveData<VirtualMachineDatabaseDto?>()

    val virtualMachine = Transformations.map(_virtualMachine) {
        it?.asDomainModel()
    }

    val virtualMachines = Transformations.map(database.virtualMachineDao.getAll()) {
        it.asDomainModel()
    }

    suspend fun refreshVirtualMachines() {
        withContext(Dispatchers.IO) {
            val virtualMachines = Network.vic.getVirtualMachines()
            database.virtualMachineDao.insertAll(*virtualMachines.asDatabaseModel())
        }
    }

    suspend fun fetchVirtualMachine(id: Int) {
        var value: VirtualMachineDatabaseDto?

        withContext(Dispatchers.IO) {
            val virtualMachine = Network.vic.getVirtualMachine(id)
            database.virtualMachineDao.update(virtualMachine.asDatabaseModel())
            value = database.virtualMachineDao.getById(id)
        }

        _virtualMachine.value = value
    }
}
