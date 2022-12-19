package be.hogent.vic.domain

import java.util.*

data class Client(
    var id: Int = 1,
    var name: String = "",
    var surName: String = "",
    var phoneNumber: String = "",
    var clientType: String = "",
    var clientOrganisation: String = "",
    var email: String = "",
    var backupContact: String = "",
    var education: String = "",
    var externalType: String = ""
)
