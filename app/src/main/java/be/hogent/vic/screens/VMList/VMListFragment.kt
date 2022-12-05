package be.hogent.vic.screens.VMList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import be.hogent.vic.R
import be.hogent.vic.databinding.FragmentVmListBinding
import be.hogent.vic.domain.VirtualMachine

class VMListFragment : Fragment() {
    private val viewModel: VMListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }

        ViewModelProvider(
            this,
            VMListViewModel.Factory(activity.application)
        ).get(VMListViewModel::class.java)
    }

    private var viewModelAdapter: VMAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.virtualMachines.observe(
            viewLifecycleOwner,
            Observer<List<VirtualMachine>> { virtualMachines ->
                virtualMachines?.apply {
                    viewModelAdapter?.virtualMachines = virtualMachines
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentVmListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_vm_list,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModelAdapter = VMAdapter()
        binding.vmList.adapter = viewModelAdapter

        return binding.root
    }

}