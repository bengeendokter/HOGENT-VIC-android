package be.hogent.vic.screens.virtualmachinerequestlist

import be.hogent.vic.domain.VirtualMachineRequest
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.databinding.VmRequestListItemBinding


class VirtualMachineRequestAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<VirtualMachineRequest, VirtualMachineRequestAdapter.VirtualMachineRequestViewHolder>(DiffCallback) {

    class VirtualMachineRequestViewHolder(private var binding: VmRequestListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(virtualMachineRequest: VirtualMachineRequest) {
            binding.request = virtualMachineRequest
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (virtualMachineRequest: VirtualMachineRequest) -> Unit){
        fun onClick(virtualMachineRequest: VirtualMachineRequest) = clickListener(virtualMachineRequest)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<VirtualMachineRequest>() {
        override fun areItemsTheSame(
            oldItem: VirtualMachineRequest,
            newItem: VirtualMachineRequest
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: VirtualMachineRequest,
            newItem: VirtualMachineRequest
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VirtualMachineRequestViewHolder {
        return VirtualMachineRequestAdapter.VirtualMachineRequestViewHolder(
            VmRequestListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: VirtualMachineRequestViewHolder, position: Int){
        val virtualMachineRequest = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(virtualMachineRequest)
        }
        holder.bind(virtualMachineRequest)
    }
}










