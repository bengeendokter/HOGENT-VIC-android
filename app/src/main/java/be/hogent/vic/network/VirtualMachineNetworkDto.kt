package be.hogent.vic.network

import be.hogent.vic.database.VirtualMachineDatabaseDto
import be.hogent.vic.domain.*
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class VirtualMachineNetworkDtoContainer(val virtualMachines: List<VirtualMachineNetworkDto>)

@JsonClass(generateAdapter = true)
data class VirtualMachineNetworkDto(
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
    val hostName: String? = null,
    val fqdn: String? = null,
    val host: String? = null,
    val ports: String? = null,
    val client: Client? = null,
    val availability: Day? = null,
    val mode: Mode? = null,
    val createdAt: Date? = null
)

fun VirtualMachineNetworkDtoContainer.asDomainModel(): List<VirtualMachine> {
    return virtualMachines.map {
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
            client = it.client?.name,
            availability = it.availability,
            mode = it.mode,
            createdAt = it.createdAt
        )
    }
}

fun VirtualMachineNetworkDtoContainer.asDatabaseModel(): Array<VirtualMachineDatabaseDto> {
    return virtualMachines.map {
        VirtualMachineDatabaseDto (
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
            client = it.client?.name,
            availability = it.availability,
            mode = it.mode,
            createdAt = it.createdAt
        )
    }.toTypedArray()
}