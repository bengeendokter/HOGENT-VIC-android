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

class ClientRepository(private val database: VicDatabase) {
    private val _client = MutableLiveData<ClientDatabaseDto?>()

    val client = Transformations.map(_client) {
        it?.asDomainModel()
    }

    val clients = Transformations.map(database.clientDao.getAll()) {
        it.asDomainModel()
    }

    suspend fun refreshClients() {
        withContext(Dispatchers.IO) {
            val clients = Network.vic.getClients()
            database.clientDao.insertAll(*clients.asDatabaseModel())
        }
    }

    suspend fun fetchClient(id: Int) {
        var value: ClientDatabaseDto?

        withContext(Dispatchers.IO) {
            val client = Network.vic.getClient(id)
            database.clientDao.update(client.asDatabaseModel())
            value = database.clientDao.getById(id)
        }

        _client.value = value
    }
}
