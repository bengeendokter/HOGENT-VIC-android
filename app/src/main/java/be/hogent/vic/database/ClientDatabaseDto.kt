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
    val surName: String,
    val phoneNumber: String,
    val clientType: String,
    val clientOrganisation: String,
    val email: String,
    val backupContact: String,
    val education: String,
    val externalType: String
)

fun ClientDatabaseDto.asDomainModel(): Client {
    return Client(
        id = id,
        name = name,
        surName = surName,
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
