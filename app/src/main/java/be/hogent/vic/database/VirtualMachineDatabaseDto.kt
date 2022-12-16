package be.hogent.vic.database

import androidx.room.*
import be.hogent.vic.domain.*
import java.util.*

@Entity(tableName = "virtual_machines")
data class VirtualMachineDatabaseDto constructor(
    // From index dto
    @PrimaryKey
    val id: Int,
    val name: String,
    val cpu: Int,
    val ram: Int,
    val storage: Int,
    val startDate: Date,
    val endDate: Date,
    val isActive: Boolean,
    val isHighlyAvailable: Boolean,
    val template: Template,
    val backupFrequency: BackupFrequency,

    // From detail dto
    val hostName: String? = null,
    val fqdn: String? = null,
    val host: String? = null,
    val ports: String? = null,
    val client: String? = null,
    val availability: Day? = null,
    val software: Software? = null,
    val createdAt: Date? = null
)

fun List<VirtualMachineDatabaseDto>.asDomainModel(): List<VirtualMachine> {
    return map {
        VirtualMachine(
            id = it.id,
            name = it.name,
            cpu = it.cpu,
            ram = it.ram,
            storage = it.storage,
            startDate = it.startDate,
            endDate = it.endDate,
            isActive = it.isActive,
            isHighlyAvailable = it.isHighlyAvailable,
            template = it.template,
            backupFrequency = it.backupFrequency,
            hostName = it.hostName,
            fqdn = it.fqdn,
            host = it.host,
            ports = it.ports,
            client = it.client,
            availability = it.availability,
            software = it.software,
            createdAt = it.createdAt
        )
    }
}