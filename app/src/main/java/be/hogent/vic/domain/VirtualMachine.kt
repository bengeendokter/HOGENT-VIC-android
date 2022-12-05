package be.hogent.vic.domain

import be.hogent.vic.network.IEnumValue
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
    val client: String? = null,
    val availability: Day? = null,
    val mode: Mode? = null,
    val createdAt: Date? = null
) {

}

enum class Template(override val value: Int) : IEnumValue {
    ARTIFICIAL_INTELLIGENCE(0),
    DATABASE(1),
    MACHINE_LEARNING(2)
}

enum class BackupFrequency(override val value: Int) : IEnumValue {
    DAILY(0),
    WEEKLY(1)
}

enum class Mode(override val value: Int) : IEnumValue {
    IAAS(0),
    PAAS(1),
    SAAS(2)
}

enum class Day(override val value: Int) : IEnumValue {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(4),
    THURSDAY(8),
    FRIDAY(16),
    SATURDAY(32),
    SUNDAY(64)
}
