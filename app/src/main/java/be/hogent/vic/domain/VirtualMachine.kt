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
    val template: Template,
    val backupFrequency: BackupFrequency,

    // From detail dto
    val hostName: String? = null,
    val fqdn: String? = null,
    val host: String? = null,
    val ports: String? = null,
    val client: Client? = null,
    val availability: Day? = null,
    val mode: Mode? = null,
    val createdAt: Date? = null
) {

}

enum class Template { ARTIFICIAL_INTELLIGENCE, DATABASE, MACHINE_LEARNING }
enum class BackupFrequency { DAILY, WEEKLY }
enum class Mode { IAAS, PAAS, SAAS }
enum class Day(val value: Int) {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(4),
    THURSDAY(8),
    FRIDAY(16),
    SATURDAY(32),
    SUNDAY(64)
}
