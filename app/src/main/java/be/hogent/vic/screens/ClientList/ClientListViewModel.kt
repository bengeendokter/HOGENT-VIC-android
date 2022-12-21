package be.hogent.vic.screens.ClientList

import android.app.Application
import androidx.lifecycle.* // ktlint-disable no-wildcard-imports
import be.hogent.vic.database.getDatabase
import be.hogent.vic.domain.Client
import be.hogent.vic.repository.ClientRepository
import kotlinx.coroutines.launch

class ClientListViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val clientRepository = ClientRepository(database)
    val clients = clientRepository.clients

    private val _navigateToDetails = MutableLiveData<Int?>()
    val navigateToDetails: LiveData<Int?>
        get() = _navigateToDetails

    init {
        viewModelScope.launch {
            clientRepository.refreshClients()
        }
    }

    fun displayDetails(client: Client) {
        _navigateToDetails.value = client.id
    }

    fun displayDetailsComplete() {
        _navigateToDetails.value = null
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ClientListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ClientListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
