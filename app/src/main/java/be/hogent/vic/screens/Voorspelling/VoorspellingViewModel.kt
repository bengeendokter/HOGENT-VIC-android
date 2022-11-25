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

class VoorspellingViewModel: ViewModel() {
    var cpu = 0

    @SuppressLint("NewApi")
    private val vms = listOf(
        VirtualMachine(
            1, "VM-IT-1", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            4, 3, 950, "IaaS",
            Date.from(LocalDate.parse("2022-11-25").atStartOfDay(systemDefault()).toInstant()),
            Date.from(LocalDate.parse("2022-11-26").atStartOfDay(systemDefault()).toInstant())
        ),
        VirtualMachine(
            1, "VM-IT-2", "VM_JN58CE_2354", "host123pt-45f", "TBD",
            2, 5, 920, "IaaS",
            Date.from(LocalDate.parse("2022-11-27").atStartOfDay(systemDefault()).toInstant()),
            SimpleDateFormat("yyyy/MM/dd").parse("2022/11/27")
        )
    )

    private val _aantalCPU = MutableLiveData<Int>()
    val aantalCPU: LiveData<Int>
        get() = _aantalCPU

    fun evaluationComplete(datum: Date): Int{
        datum.getDayOf
    }
}