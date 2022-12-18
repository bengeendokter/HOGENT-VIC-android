package be.hogent.vic.screens.virtualmachine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import be.hogent.vic.databinding.FragmentVirtualMachineBinding

class VirtualMachineFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVirtualMachineBinding.inflate(inflater)
        val selectedId = VirtualMachineFragmentArgs.fromBundle(requireArguments()).selectedId
        val viewModel = ViewModelProvider(
            this,
            VirtualMachineViewModel.Factory(requireNotNull(this.activity).application, selectedId)
        ).get(VirtualMachineViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.virtualMachine.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.vm = it
            }
        })

        return binding.root
    }
}