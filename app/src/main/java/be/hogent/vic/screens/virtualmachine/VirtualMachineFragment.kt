package be.hogent.vic.screens.virtualmachine

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

    /* Tijdelijk */
    private fun setTexts() {
        binding.apply {
            // klant info
            vmGebruikerIn.text = "Marc Asselberg"
            vmTxtBeginDatumIn.text = "01/01/2023"
            vmTxtEindDatumIn.text = "01/02/2023"

            // stats info
            vmTxtStorageIn.text = "20 GB"
            vmTxtMemoryIn.text = "2 GB"
            vmTxtCpuCoresIn.text = "4"
            vmTxtModeIn.text = "IaaS"

            // vm info
            vmTxtNameIn.text = "virtual-machine-hogent-1"
            vmTxtHostnameIn.text = "vik1"
            vmTxtFQDNIn.text = "devops-proj1.vichogent.be"
            vmTxtTemplateIn.text = "standaard"
        }
    }

}