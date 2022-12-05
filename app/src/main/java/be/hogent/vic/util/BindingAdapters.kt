package be.hogent.vic.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.domain.VirtualMachine
import be.hogent.vic.screens.virtualmachinelist.VirtualMachineAdapter
import java.text.SimpleDateFormat

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<VirtualMachine>?) {
    val adapter = recyclerView.adapter as VirtualMachineAdapter
    adapter.submitList(data)
}

@BindingAdapter("client")
fun bindClient(textView: TextView, client: String?) {
    textView.text = client ?: "Geen klant"
}

@BindingAdapter("capacity")
fun bindCapacity(textView: TextView, amount: Int?) {
    textView.text = "%d GB".format(amount ?: 0)
}

@BindingAdapter("cores")
fun bindCores(textView: TextView, amount: Int?) {
    textView.text = "%d Cores".format(amount ?: 0)
}

@BindingAdapter("dateRange")
fun bindDateRange(textView: TextView, vm: VirtualMachine?) {
    vm ?: return
    textView.text = "%s - %s".format(
        SimpleDateFormat("dd/MM/yyyy").format(vm.startDate),
        SimpleDateFormat("dd/MM/yyyy").format(vm.endDate)
    )
}