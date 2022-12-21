package be.hogent.vic.screens.clientList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.databinding.FragmentClientListItemBinding
import be.hogent.vic.domain.Client

class ClientAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Client, ClientAdapter.ClientViewHolder>(DiffCallback) {
    class ClientViewHolder(private var binding: FragmentClientListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(client: Client) {
            binding.client = client
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (client: Client) -> Unit) {
        fun onClick(client: Client) = clickListener(client)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Client>() {
        override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder(
            FragmentClientListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(client)
        }
        holder.bind(client)
    }
}
