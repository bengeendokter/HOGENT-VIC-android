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
    val availability: Int? = null,
    val software: Int? = null,
    val createdAt: Date? = null
) {

}

enum class Template(override val value: Int) : IEnumValue {
    MYSQL_DATABASE(0),
    MONGODB(1),
    DOCKER_LINUX(2),
    DOCKER_WINDOWS(3)
}

enum class BackupFrequency(override val value: Int) : IEnumValue {
    DAILY(0),
    WEEKLY(1)
}

enum class Software(override val value: Int) : IEnumValue {
    WINDOWS(1),
    LINUX(2),
    MYSQL(4),
    MONGODB(8),
    DOCKER(16),
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
