package be.hogent.vic.screens.Client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.hogent.vic.databinding.FragmentClientBinding

class ClientFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentClientBinding.inflate(inflater)
        val selectedId = ClientListFragmentArgs.fromBundle(requireArguments()).selectedId
        val viewModel = ViewModelProvider(
            this,
            ClientViewModel.Factory(requireNotNull(this.activity).application, selectedId)
        ).get(ClientViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.client.observe(
            viewLifecycleOwner,
            Observer {
                if(it != null){
                    binding.client = it
                }
            }
        )
        return binding.root
    }
}
