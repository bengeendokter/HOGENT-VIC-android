package be.hogent.vic.domain

import be.hogent.vic.network.IEnumValue
import java.util.Date

data class VirtualMachineRequest(
    val id: Int,
    val projectName: String,
    val date: Date,
    val startDate: Date,
    val client: String? = null,
    val virtualMachineId: Int? = null,
    val status: Status,

    val endDate: Date? = null,
    val reason: String? = null
)

enum class Status(override val value: Int): IEnumValue{
    ACCEPTED(0),
    DENIED(1),
    HANDLED(2),
    REQUESTED(4)
}
