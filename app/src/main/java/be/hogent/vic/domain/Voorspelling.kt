package be.hogent.vic.domain

import java.util.*

data class Voorspelling (
    var totaal: Array<String> = Array(3) { "0" },
    var vrij: Array<String> = Array(3) { "0" }
) {

}