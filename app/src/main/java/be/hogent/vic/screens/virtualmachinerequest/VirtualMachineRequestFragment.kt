package be.hogent.vic.screens.virtualmachinerequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import be.hogent.vic.R
import be.hogent.vic.databinding.FragmentVirtualMachineRequestBinding
import be.hogent.vic.domain.Status
import be.hogent.vic.domain.VirtualMachineRequest
import java.time.LocalDate
import java.util.Date

class VirtualMachineRequestFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVirtualMachineRequestBinding.inflate(inflater)
        val selectedId = VirtualMachineRequestFragmentArgs.fromBundle(requireArguments()).selectedId
        val viewModelFactory = ViewModelProvider(
            this,
            VirtualMachineRequestViewModel.VirtualMachineRequestFactory(selectedId, requireNotNull(this.activity).application)
        ).get(VirtualMachineRequestViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        viewModelFactory.virtualMachineRequest.observe(viewLifecycleOwner, Observer{
            if(it != null) {
                binding.request = it
            }
        })

        return binding.root
    }

}