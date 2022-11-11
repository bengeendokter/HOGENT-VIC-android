package be.hogent.vic.VMList

/*
zal nodig zijn voor recyclerview
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.R

class VMAdapter: RecyclerView.Adapter<VMAdapter.ViewHolder>() {

    var data = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8)
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
            item: Int
        ) {
            clientName.text = item.toString()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_vm_list_item, parent, false)
                return ViewHolder(view)
            }
        }
    }

}