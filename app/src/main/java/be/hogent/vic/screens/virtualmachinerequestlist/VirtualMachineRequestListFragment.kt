package be.hogent.vic.screens.virtualmachinerequestlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.hogent.vic.databinding.FragmentVmRequestListBinding
import be.hogent.vic.screens.virtualmachinelist.VirtualMachineAdapter

class VirtualMachineRequestListFragment : Fragment(){
    private val viewModel: VirtualMachineRequestListViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }

        ViewModelProvider(
            this,
            VirtualMachineRequestListViewModel.Factory(activity.application)
        ).get(VirtualMachineRequestListViewModel::class.java)
    }

    private var viewModelAdapter: VirtualMachineRequestAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVmRequestListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModelAdapter = VirtualMachineRequestAdapter(VirtualMachineRequestAdapter.OnClickListener{
            viewModel.displayDetails(it)
        })
        binding.vmRequestList.adapter = viewModelAdapter
        binding.viewModel = viewModel

//        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
//            if (it != null){
//                this.findNavController().navigate()
//            }
//        })
        return binding.root
    }
}