package be.hogent.vic.screens.virtualmachinelist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.R
import be.hogent.vic.domain.VirtualMachine
import java.text.SimpleDateFormat

class VirtualMachineAdapter: RecyclerView.Adapter<VirtualMachineAdapter.ViewHolder>() {
    var virtualMachines: List<VirtualMachine> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = virtualMachines.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = virtualMachines[position]
        holder.bind(item)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val clientName: TextView = itemView.findViewById(R.id.vmli_txt_clientName)
        val vmName: TextView = itemView.findViewById(R.id.vmli_txt_vmName)
        val startEndDate: TextView = itemView.findViewById(R.id.vmli_txt_startEndDate)

        val vCpu: TextView = itemView.findViewById(R.id.vmli_txt_vCPU)
        val ram: TextView = itemView.findViewById(R.id.vmli_txt_ram)
        val storage: TextView = itemView.findViewById(R.id.vmli_txt_storage)

        fun bind(
            vm: VirtualMachine
        ) {
            clientName.text = vm.client ?: "Geen klant"
            vmName.text = vm.name
            startEndDate.text = "%s - %s".format(
                SimpleDateFormat("dd/MM/yyyy").format(vm.startDate),
                SimpleDateFormat("dd/MM/yyyy").format(vm.endDate)
            )
            vCpu.text = "%d Cores".format(vm.cpu)
            ram.text = "%d GB".format(vm.ram)
            storage.text = "%d GB".format(vm.storage)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_vm_list_item, parent, false)
                view.setOnClickListener { view: View ->
                    view.findNavController().navigate(VirtualMachineListFragmentDirections.actionVMListFragmentToVirtualMachineFragment())
                }
                return ViewHolder(view)
            }
        }
    }

}