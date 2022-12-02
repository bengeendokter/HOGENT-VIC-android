package be.hogent.vic.screens.Client

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.domain.Client
import be.hogent.vic.screens.VMList.VMAdapter.ViewHolder.Companion.from

class ClientAdapter: RecyclerView.Adapter<ClientAdapter.ViewHolder>() {

    var data = listOf<Client>(
        Client(
            1,
            "Bob",
            "De Smet",
            "+32 123 45 67 89",
            "external",
            "VOKA",
            "bobdesmet@voka.be",
            "bartdewolf@voka.be",
            "",
            "Manager"
        ),
        Client(
            2,
            "Bart",
            "De Wolf",
            "+32 012 34 56 78",
            "external",
            "VOKA",
            "bartdewolf@voka.be",
            "bobdesmet@voka.be",
            "",
            "CEO"
        ),
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
        val Name: TextView = itemView.findViewById()
        // TODO
    }

}

//    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val clientName: TextView = itemView.findViewById(R.id.vmli_txt_clientName)
//        val vmName: TextView = itemView.findViewById(R.id.vmli_txt_vmName)
//        val startEndDate: TextView = itemView.findViewById(R.id.vmli_txt_startEndDate)
//
//        val vCpu: TextView = itemView.findViewById(R.id.vmli_txt_vCPU)
//        val ram: TextView = itemView.findViewById(R.id.vmli_txt_ram)
//        val storage: TextView = itemView.findViewById(R.id.vmli_txt_storage)
//        val mode: TextView = itemView.findViewById(R.id.vmli_txt_mode)
//
//        fun bind(
//            item: VirtualMachine
//        ) {
//            clientName.text = "Unizo 1" // !TODO
//            vmName.text = item.Name
//            startEndDate.text = "%s - %s".format(
//                SimpleDateFormat("dd/MM/yyyy").format(item.startDate),
//                SimpleDateFormat("dd/MM/yyyy").format(item.endDate)
//            )
//            vCpu.text = item.cpu.toString()
//            ram.text = "%d GB".format(item.ram)
//            storage.text = "%d GB".format(item.storage)
//            mode.text = item.mode
//
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.fragment_vm_list_item, parent, false)
//                view.setOnClickListener { view: View ->
//                    view.findNavController().navigate(VMListFragmentDirections.actionVMListFragmentToVirtualMachineFragment())
//                }
//                return ViewHolder(view)
//            }
//        }
//    }