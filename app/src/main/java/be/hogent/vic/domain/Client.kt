package be.hogent.vic.domain

import java.util.*

data class Client(
    var id: Int = 1,
    var name: String = "",
    var surName: String = "",
    var phoneNumber: String = "",
    var clientType: Int = 1,
    var clientOrganisation: String = "",
    // client dto
    var email: String? = null,
    var backupContact: String? = null,
    var education: String? = null,
    var externalType: String? = null
)
