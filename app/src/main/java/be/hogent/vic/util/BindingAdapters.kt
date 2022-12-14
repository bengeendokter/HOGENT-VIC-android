package be.hogent.vic.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.R
import be.hogent.vic.domain.BackupFrequency
import be.hogent.vic.domain.VirtualMachine
import be.hogent.vic.domain.VirtualMachineRequest
import be.hogent.vic.screens.virtualmachinelist.VirtualMachineAdapter
import be.hogent.vic.screens.virtualmachinerequestlist.VirtualMachineRequestAdapter
import java.text.SimpleDateFormat

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<VirtualMachine>?) {
    val adapter = recyclerView.adapter as VirtualMachineAdapter
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

@BindingAdapter("dateRange")
fun bindDateRange(textView: TextView, vm: VirtualMachine?) {
    vm ?: return
    textView.text = "%s - %s".format(
        SimpleDateFormat("dd/MM/yyyy").format(vm.startDate),
        SimpleDateFormat("dd/MM/yyyy").format(vm.endDate)
    )
}