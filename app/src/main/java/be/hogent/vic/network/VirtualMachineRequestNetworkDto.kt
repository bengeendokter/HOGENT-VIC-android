package be.hogent.vic.network

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

fun List<VirtualMachineRequestNetworkDto>.asDomainModel(): List<VirtualMachineRequest>{
    return map{
        VirtualMachineRequest(
            id = it.id,
            projectName = it.projectName,
            date = it.date,
            startDate = it.startDate,
            client = it.client?.name + " " + it.client?.surname,
            virtualMachineId = it.virtualMachineId,
            status = it.status,
            endDate = it.endDate,
            reason = it.reason
        )
    }
}

fun List<VirtualMachineRequestNetworkDto>.asDatabaseModel(): Array<VirtualMachineRequestDatabaseDto>{
    return map{
        VirtualMachineRequestDatabaseDto(
            id = it.id,
            projectName = it.projectName,
            date = it.date,
            startDate = it.startDate,
            client = it.client?.name + " " + it.client?.surname,
            virtualMachineId = it.virtualMachineId,
            status = it.status,
            endDate = it.endDate,
            reason = it.reason
        )
    }.toTypedArray()
}