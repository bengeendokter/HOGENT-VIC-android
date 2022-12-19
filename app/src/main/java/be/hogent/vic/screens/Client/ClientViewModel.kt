package be.hogent.vic.screens.Client

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import be.hogent.vic.database.getDatabase
import be.hogent.vic.repository.ClientRepository
import kotlinx.coroutines.launch

class ClientViewModel(application: Application, private val clientId: Int) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val clientRepository = ClientRepository(database)
    val client = clientRepository.client

    init {
        viewModelScope.launch {
            clientRepository.fetchClient(clientId)
        }
    }

    class Factory(val app: Application, private val clientId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ClientViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ClientViewModel(app, clientId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
