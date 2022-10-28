package be.hogent.vic.VirtualMachine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import be.hogent.vic.R
import be.hogent.vic.databinding.FragmentVirtualMachineBinding

/**
 * A simple [Fragment] subclass.
 * Use the [VirtualMachineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VirtualMachineFragment : Fragment() {

    private lateinit var binding: FragmentVirtualMachineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_virtual_machine, container, false
        )
        setTexts()
        return binding.root
    }

    private fun setTexts() {
        binding.apply {
            txtCpuCoresCurrent.text = "2"
            txtMemoryCurrent.text = "2 GB"
            txtStorageCurrent.text = "20GB"
            txtModeCurrent.text = "IaaS"
            txtNameCurrent.text="virtual-machine-hogent-1"
            txtHostnameCurrent.text = "vik1"
            txtFqdnCurrent.text = "devops-proj1.vichogent.be"
            txtTemplateCurrent.text = "Standaard"
        }
    }

}