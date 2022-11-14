package be.hogent.vic.screens.VMList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import be.hogent.vic.R
import be.hogent.vic.databinding.FragmentVmListItemBinding

/**
 * A simple [Fragment] subclass.
 * Use the [VMListItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VMListItemFragment : Fragment() {

    private lateinit var binding: FragmentVmListItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_vm_list_item, container, false
        )
        return binding.root
    }
}