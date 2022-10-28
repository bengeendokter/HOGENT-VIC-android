package be.hogent.vic.VMList

/*
zal nodig zijn voor recyclerview
 */

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import be.hogent.vic.R
//
//class VMAdapter: RecyclerView.Adapter<VMAdapter.ViewHolder>() {
//
//    var data = listOf<VirtualMachine>()
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent);
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = data[position]
//        holder.bind(item)
//    }
//
//    class ViewHolder private constructor (itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val vmName: TextView = itemView.findViewById(R.id.txt_vmName)
//        val clientName: TextView = itemView.findViewById(R.id.txt_clientName)
//        val vmIcon: ImageView = itemView.findViewById(R.id.img_vmIcon)
//    }
//
//    companion object {
//        fun from(parent: ViewGroup): ViewHolder {
//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.list_item_vm, parent, false)
//            return ViewHolder(view)
//        }
//    }
//}