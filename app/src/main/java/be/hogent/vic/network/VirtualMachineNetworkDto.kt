package be.hogent.vic.network

import be.hogent.vic.database.VirtualMachineDatabaseDto
import be.hogent.vic.domain.*
import com.squareup.moshi.JsonClass
import java.util.*

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
    val software: Software? = null,
    val createdAt: String? = null // temp
)

fun VirtualMachineNetworkDto.asDatabaseModel(): VirtualMachineDatabaseDto {
    return VirtualMachineDatabaseDto(
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
        client = client?.name,
        availability = availability,
        software = software,
        createdAt = Date() //createdAt
    )
}

fun List<VirtualMachineNetworkDto>.asDatabaseModel(): Array<VirtualMachineDatabaseDto> {
    return map {
        it.asDatabaseModel()
    }.toTypedArray()
}