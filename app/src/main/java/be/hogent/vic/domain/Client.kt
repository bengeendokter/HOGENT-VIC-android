package be.hogent.vic.domain

import java.util.*

data class Client(
    var Id: Int = 1,
    var Name: String = "",
    var SurName: String = "",
    var PhoneNumber: String = "",
    var ClientType: String = "",
    var ClientOrganisation: String = "",
    var Email: String = "",
    var BackupContact: String = "",
    var Education: String = "",
    var ExternalType: String = "",
) {

}