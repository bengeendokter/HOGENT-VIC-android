package be.hogent.vic.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.hogent.vic.domain.* // ktlint-disable no-wildcard-imports
import java.util.*

@Entity(tableName = "clients")
data class ClientDatabaseDto constructor(
    // From index dto
    @PrimaryKey
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val clientType: String,
    val clientOrganisation: String,
    val email: String? = null,
    val backupContact: String? = null,
    val education: String? = null,
    val externalType: String? = null
)

fun ClientDatabaseDto.asDomainModel(): Client {
    return Client(
        id = id,
        name = name,
        surName = surname,
        phoneNumber = phoneNumber,
        clientType = clientType,
        clientOrganisation = clientOrganisation,
        email = email,
        backupContact = backupContact,
        education = education,
        externalType = externalType
    )
}

fun List<ClientDatabaseDto>.asDomainModel(): List<Client> {
    return map {
        it.asDomainModel()
    }
}
