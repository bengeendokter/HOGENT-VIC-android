package be.hogent.vic.screens.VMList

/*
zal nodig zijn voor recyclerview
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.R
import be.hogent.vic.domain.VirtualMachine
import java.text.SimpleDateFormat
import java.util.Date

class VMAdapter: RecyclerView.Adapter<VMAdapter.ViewHolder>() {

    var data = listOf<VirtualMachine>(
        VirtualMachine(
            1,
            "VM-IT-1",
            "VM_JN58CE_2354",
            "vik1",
            "TBD",
            4,
            3,
            950,
            "IaaS",
            Date(2022, 1, 15),
            Date(2023, 4, 14),
        ),
        VirtualMachine(
            2,
            "VM-IT-2",
            "VM_GH35ZR_5436",
            "vik1",
            "TBD",
            2,
            5,
            920,
            "IaaS",
            Date(2022, 2, 16),
            Date(2023, 3, 17),
        )
    )
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val clientName: TextView = itemView.findViewById(R.id.vmli_txt_clientName)
        val vmName: TextView = itemView.findViewById(R.id.vmli_txt_vmName)
        val startEndDate: TextView = itemView.findViewById(R.id.vmli_txt_startEndDate)

        val vCpu: TextView = itemView.findViewById(R.id.vmli_txt_vCPU)
        val ram: TextView = itemView.findViewById(R.id.vmli_txt_ram)
        val storage: TextView = itemView.findViewById(R.id.vmli_txt_storage)
        val mode: TextView = itemView.findViewById(R.id.vmli_txt_mode)

        fun bind(
            item: VirtualMachine
        ) {
            clientName.text = "Unizo 1" // !TODO
            vmName.text = item.Name
            startEndDate.text = "%s - %s".format(
                SimpleDateFormat("dd/MM/yyyy").format(item.startDate),
                SimpleDateFormat("dd/MM/yyyy").format(item.endDate)
            )
            vCpu.text = item.cpu.toString()
            ram.text = "%d GB".format(item.ram)
            storage.text = "%d GB".format(item.storage)
            mode.text = item.mode

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_vm_list_item, parent, false)
                view.setOnClickListener { view: View ->
                    view.findNavController().navigate(VMListFragmentDirections.actionVMListFragmentToVirtualMachineFragment())
                }
                return ViewHolder(view)
            }
        }
    }

}