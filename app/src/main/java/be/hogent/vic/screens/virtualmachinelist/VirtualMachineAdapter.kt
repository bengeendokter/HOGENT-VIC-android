package be.hogent.vic.screens.virtualmachinelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.databinding.VmListItemBinding
import be.hogent.vic.domain.VirtualMachine

class VirtualMachineAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<VirtualMachine, VirtualMachineAdapter.VirtualMachineViewHolder>(DiffCallback) {
    class VirtualMachineViewHolder(private var binding: VmListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(virtualMachine: VirtualMachine) {
            binding.vm = virtualMachine
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (virtualMachine: VirtualMachine) -> Unit) {
        fun onClick(virtualMachine: VirtualMachine) = clickListener(virtualMachine)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<VirtualMachine>() {
        override fun areItemsTheSame(oldItem: VirtualMachine, newItem: VirtualMachine): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: VirtualMachine, newItem: VirtualMachine): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VirtualMachineViewHolder {
        return VirtualMachineViewHolder(
            VmListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VirtualMachineViewHolder, position: Int) {
        val virtualMachine = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(virtualMachine)
        }
        holder.bind(virtualMachine)
    }
}