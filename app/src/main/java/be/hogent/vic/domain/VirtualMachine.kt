package be.hogent.vic.domain

import java.util.Date

data class VirtualMachine(
    // From index dto
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

    // From detail dto
    val hostName: String? = null,
    val fqdn: String? = null,
    val host: String? = null,
    val ports: String? = null,
    // val client: String? = null,
    // val availability: String? = null,
    // val mode: String? = null,
    val createdAt: Date? = null
) {

}
