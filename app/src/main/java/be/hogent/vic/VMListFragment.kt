package be.hogent.vic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import be.hogent.vic.databinding.FragmentHomeBinding
import be.hogent.vic.databinding.FragmentVmListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [VMListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VMListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentVmListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_vm_list, container, false
        )
        return binding.root
    }

}