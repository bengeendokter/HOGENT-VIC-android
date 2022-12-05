package be.hogent.vic.domain

import java.time.DateTimeException
import java.util.Date

data class VirtualMachine(
    val id: Int,
    val name: String,
    val hostName: String,
    val host: String,
    val fqdn: String,
    val cpu: Int,
    val ram: Int,
    val storage: Int,
    val ports: String,
    // val template: String,
    // val mode: String,
    // val availability: String,
    // val backupFrequency: String,
    // val client: String,
    val isActive: Boolean,
    val isHighlyAvailable: Boolean,
    val startDate: Date,
    val endDate: Date,
    val createdAt: Date
) {

}
