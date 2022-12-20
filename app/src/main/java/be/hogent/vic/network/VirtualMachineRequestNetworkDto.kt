package be.hogent.vic.network

import be.hogent.vic.database.VirtualMachineDatabaseDto
import be.hogent.vic.database.VirtualMachineRequestDatabaseDto
import be.hogent.vic.domain.Client
import be.hogent.vic.domain.Status
import be.hogent.vic.domain.VirtualMachineRequest
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class VirtualMachineRequestNetworkDto(
    val id: Int,
    val projectName: String,
    val date: Date,
    val startDate: Date,
    val client: Client? = null,
    val virtualMachineId: Int? = null,
    val status: Status,

    val endDate: Date? = null,
    val reason: String? = null
)


fun VirtualMachineRequestNetworkDto.asDatabaseModel(): VirtualMachineRequestDatabaseDto {
    return VirtualMachineRequestDatabaseDto(
        id = id,
        projectName = projectName,
        date = date,
        startDate = startDate,
        client = client?.name + " " + client?.surName,
        clientOrg = client?.clientOrganisation,
        clientNmr = client?.phoneNumber,
        virtualMachineId = virtualMachineId,
        status = status,
        endDate = endDate,
        reason = reason
    )
}

fun List<VirtualMachineRequestNetworkDto>.asDatabaseModel(): Array<VirtualMachineRequestDatabaseDto> {
    return map {
        it.asDatabaseModel()
    }.toTypedArray()
}