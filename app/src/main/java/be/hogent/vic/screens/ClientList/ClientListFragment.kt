package be.hogent.vic.screens.ClientList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import be.hogent.vic.R
import be.hogent.vic.databinding.FragmentClientListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ClientListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClientListFragment : Fragment() {

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val binding: FragmentVmListBinding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_vm_list, container, false
//        )
//
//        val adapter = VMAdapter()
//        binding.vmList.adapter = adapter
//
//        binding.lifecycleOwner = viewLifecycleOwner
//
//        return binding.root
//    }
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentClientListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_client_list, container, false
        )

    // TODO uncomment?
//        val adapter = ClientAdapter()
//        binding.clientList.adapter = adapter

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}