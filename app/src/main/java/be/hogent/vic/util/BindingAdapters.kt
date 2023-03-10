package be.hogent.vic.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.R
import be.hogent.vic.domain.* // ktlint-disable no-wildcard-imports
import be.hogent.vic.screens.clientList.ClientAdapter
import be.hogent.vic.screens.virtualmachinelist.VirtualMachineAdapter
import be.hogent.vic.screens.virtualmachinerequestlist.VirtualMachineRequestAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.EnumMap

val dayMap = EnumMap(
    mapOf(
        Day.MONDAY to "Ma",
        Day.TUESDAY to "Di",
        Day.WEDNESDAY to "Woe",
        Day.THURSDAY to "Do",
        Day.FRIDAY to "Vri",
        Day.SATURDAY to "Za",
        Day.SUNDAY to "Zo"
    )
)

val softwareMap = EnumMap(
    mapOf(
        Software.DOCKER to "Docker",
        Software.LINUX to "Linux",
        Software.MONGODB to "MongoDB",
        Software.MYSQL to "MySQL",
        Software.WINDOWS to "Windows"
    )
)

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<VirtualMachine>?) {
    val adapter = recyclerView.adapter as VirtualMachineAdapter
    adapter.submitList(data)
}

@BindingAdapter("clientlistData")
fun bindRecyclerViewClient(recyclerView: RecyclerView, data: List<Client>?) {
    val adapter = recyclerView.adapter as ClientAdapter
    adapter.submitList(data)
}

@BindingAdapter("requestListData")
fun bindRecyclerViewRequests(recyclerView: RecyclerView, data: List<VirtualMachineRequest>?) {
    val adapter = recyclerView.adapter as VirtualMachineRequestAdapter
    adapter.submitList(data)
}

@BindingAdapter("client")
fun bindClient(textView: TextView, client: String?) {
    textView.text = client ?: "Geen klant"
}

@BindingAdapter("status")
fun bindStatus(textView: TextView, status: Boolean?) {
    textView.setTextColor(textView.context.getColor(if (status == true) R.color.green else R.color.red))
    textView.text = if (status == true) "Aan" else "Uit"
}

@BindingAdapter("backup")
fun bindBackup(textView: TextView, backupFrequency: BackupFrequency?) {
    textView.text = when (backupFrequency) {
        BackupFrequency.WEEKLY -> "Wekelijkse backup"
        BackupFrequency.DAILY -> "Dagelijkse backup"
        null -> ""
    }
}

@BindingAdapter("capacity")
fun bindCapacity(textView: TextView, amount: Int?) {
    textView.text = "%d GB".format(amount ?: 0)
}

@BindingAdapter("cores")
fun bindCores(textView: TextView, amount: Int?) {
    textView.text = "%d vCPU".format(amount ?: 0)
}

@BindingAdapter("availability")
fun bindAvailability(textView: TextView, availability: Int?) {
    availability ?: return
    var textList = listOf<String>()

    Day.values().filter { availability.hasFlag(it.value) }.forEach {
        textList = textList.plus(dayMap[it] ?: "")
    }

    textView.text = textList.joinToString(", ")
}

@BindingAdapter("software")
fun bindSoftware(textView: TextView, software: Int?) {
    software ?: return
    var textList = listOf<String>()

    Software.values().filter { software.hasFlag(it.value) }.forEach {
        textList = textList.plus(softwareMap[it] ?: "")
    }

    textView.text = textList.joinToString(", ")
}

@BindingAdapter("highlyavailable")
fun bindHighlyAvailable(textView: TextView, highlyAvailable: Boolean?) {
    highlyAvailable ?: return
    textView.text = if (highlyAvailable) "Ja" else "Nee"
}

@BindingAdapter(value = ["date", "endDate"], requireAll = false)
fun bindDate(textView: TextView, date: Date?, endDate: Date?) {
    date ?: return

    if (endDate == null) {
        textView.text = SimpleDateFormat("dd/MM/yyyy").format(date)
        return
    }
    textView.text = "%s - %s".format(
        SimpleDateFormat("dd/MM/yyyy").format(date),
        SimpleDateFormat("dd/MM/yyyy").format(endDate)
    )
}

fun Int.hasFlag(flag: Int): Boolean {
    return ((this and flag) == flag)
}
