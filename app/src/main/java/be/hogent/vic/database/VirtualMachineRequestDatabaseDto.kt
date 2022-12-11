package be.hogent.vic.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.hogent.vic.domain.Status
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
    val virtualMachineId: Int? = null,
    val status: Status,

    val endDate: Date? = null,
    val reason: String? = null
)

fun List<VirtualMachineRequestDatabaseDto>.asDomainModel(): List<VirtualMachineRequest>{
    return map{
        VirtualMachineRequest(
            id = it.id,
            projectName = it.projectName,
            date = it.date,
            startDate = it.startDate,
            client = it.client,
            virtualMachineId = it.virtualMachineId,
            status = it.status,
            endDate = it.endDate,
            reason = it.reason
        )
    }
}