package be.hogent.vic.screens.Voorspelling

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hogent.vic.domain.VirtualMachine
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneId.systemDefault
import java.util.*
import kotlin.time.Duration.Companion.days

class VoorspellingViewModel: ViewModel() {
    var cpu = 0

    @SuppressLint("NewApi")
    private val vms = listOf(
        VirtualMachine(
            1, "VM-IT-1", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            4, 3, 950, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/25"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/26")
        ),
        VirtualMachine(
            1, "VM-IT-2", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            2, 5, 920, "IaaS",
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/27"),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/29")
        )
    )

    private val _aantalCPU = MutableLiveData<Int>()
    val aantalCPU: LiveData<Int>
        get() = _aantalCPU

    fun geefcpu(datum: Date): String{
        var cal: Calendar = Calendar.getInstance()
        cal.setTimeInMillis(datum.getTime())
        var num = (cal.get(Calendar.DAY_OF_MONTH) + 1)
        return num.toString()
    }
}