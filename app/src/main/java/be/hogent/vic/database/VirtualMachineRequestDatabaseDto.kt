package be.hogent.vic.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.hogent.vic.domain.Status
import be.hogent.vic.domain.VirtualMachine
import be.hogent.vic.domain.VirtualMachineRequest
import java.util.*

@Entity(tableName = "virtual_machine_requests")
data class VirtualMachineRequestDatabaseDto constructor(
    @PrimaryKey
    val id: Int,
    val projectName: String,
    val date: Date,
    val startDate: Date,
    val client: String? = null,
    val clientEmail: String? = null,
    val clientNmr: String? = null,
    val clientOrg: String? = null,
    val virtualMachineId: Int? = null,
    val status: Status,

    val endDate: Date? = null,
    val reason: String? = null
)

fun VirtualMachineRequestDatabaseDto.asDomainModel(): VirtualMachineRequest {
    return VirtualMachineRequest(
        id = id,
        projectName = projectName,
        date = date,
        startDate = startDate,
        client = client,
        clientOrg = clientOrg,
        clientNmr = clientNmr,
        virtualMachineId = virtualMachineId,
        status = status,
        endDate = endDate,
        reason = reason
    )
}

fun List<VirtualMachineRequestDatabaseDto>.asDomainModel(): List<VirtualMachineRequest> {
    return map {
        it.asDomainModel()
    }
}