package be.hogent.vic.screens.virtualmachinelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.hogent.vic.databinding.FragmentVmListBinding

class VirtualMachineListFragment : Fragment() {
    private val viewModel: VirtualMachineListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }

        ViewModelProvider(
            this,
            VirtualMachineListViewModel.Factory(activity.application)
        ).get(VirtualMachineListViewModel::class.java)
    }

    private var viewModelAdapter: VirtualMachineAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVmListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModelAdapter = VirtualMachineAdapter(
            VirtualMachineAdapter.OnClickListener {
                viewModel.displayDetails(it)
            }
        )
        binding.vmList.adapter = viewModelAdapter
        binding.viewModel = viewModel

        viewModel.navigateToDetails.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    this.findNavController().navigate(VirtualMachineListFragmentDirections.actionVMListFragmentToVirtualMachineFragment(it))
                    viewModel.displayDetailsComplete()
                }
            }
        )

        return binding.root
    }
}
