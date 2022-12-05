package be.hogent.vic.domain

import java.time.DateTimeException
import java.util.Date

data class VirtualMachine(
    val id: Int,
    val name: String,
    val cpu: Int,
    val ram: Int,
    val storage: Int,
    val startDate: Date,
    val endDate: Date,
    val isActive: Boolean,
    val isHighlyAvailable: Boolean,
    // val template: String,
    // val backupFrequency: String,

    val hostName: String?,
    val fqdn: String?,
    val host: String?,
    val ports: String?,
    // val client: String?,
    // val availability: String?,
    // val mode: String?,
    val createdAt: Date?
) {
    // From index dto
    constructor(
        id: Int,
        name: String,
        cpu: Int,
        ram: Int,
        storage: Int,
        startDate: Date,
        endDate: Date,
        isActive: Boolean,
        isHighlyAvailable: Boolean /*, template: String, backupFrequency: String */
    ) : this(
        id,
        name,
        cpu,
        ram,
        storage,
        startDate,
        endDate,
        isActive,
        isHighlyAvailable,
        // template,
        // backupFrequency,
        null,
        null,
        null,
        null,
        // null,
        // null,
        // null,
        null
    ) {

    }
}
