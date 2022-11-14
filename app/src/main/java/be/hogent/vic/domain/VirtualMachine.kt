package be.hogent.vic.domain

import java.time.DateTimeException
import java.util.Date

data class VirtualMachine(
    var Id: Int = 1,
    var Name: String = "",
    var hostName: String = "",
    var host: String = "",
    var fqdn: String = "",
    var cpu: Int = 0,
    var ram: Int = 0,
    var storage: Int = 0,
//    var poorten: String = "",
//    var template: String = "",
    var mode: String = "",
    var startDate: Date = Date(),
    var endDate: Date = Date(),
) {

}
