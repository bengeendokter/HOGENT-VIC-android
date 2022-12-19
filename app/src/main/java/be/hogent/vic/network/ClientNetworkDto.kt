package be.hogent.vic.network

import be.hogent.vic.database.ClientDatabaseDto
import be.hogent.vic.domain.* // ktlint-disable no-wildcard-imports
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class ClientNetworkDto(
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

fun ClientNetworkDto.asDatabaseModel(): ClientDatabaseDto {
    return ClientDatabaseDto(
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

fun List<ClientNetworkDto>.asDatabaseModel(): Array<ClientDatabaseDto> {
    return map {
        it.asDatabaseModel()
    }.toTypedArray()
}
