package be.hogent.vic.database
// ktlint-disable import-ordering
import androidx.room.*
// ktlint-disable no-wildcard-imports
import be.hogent.vic.domain.*
// ktlint-disable no-wildcard-imports
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
    val availability: Int? = null,
    val software: Int? = null,
    val createdAt: Date? = null
)

fun VirtualMachineDatabaseDto.asDomainModel(): VirtualMachine {
    return VirtualMachine(
        id = id,
        name = name,
        cpu = cpu,
        ram = ram,
        storage = storage,
        startDate = startDate,
        endDate = endDate,
        isActive = isActive,
        isHighlyAvailable = isHighlyAvailable,
        template = template,
        backupFrequency = backupFrequency,
        hostName = hostName,
        fqdn = fqdn,
        host = host,
        ports = ports,
        client = client,
        availability = availability,
        software = software,
        createdAt = createdAt
    )
}

fun List<VirtualMachineDatabaseDto>.asDomainModel(): List<VirtualMachine> {
    return map {
        it.asDomainModel()
    }
}
