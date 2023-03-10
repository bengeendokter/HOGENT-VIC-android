package be.hogent.vic.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import be.hogent.vic.R
import be.hogent.vic.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.navBtnVirtualMachines.setOnClickListener { view: View ->
            view.findNavController().navigate(be.hogent.vic.screens.home.HomeFragmentDirections.actionHomeFragmentToVMListFragment())
        }

        binding.navBtnKlanten.setOnClickListener { view: View ->
            view.findNavController().navigate(be.hogent.vic.screens.home.HomeFragmentDirections.actionHomeFragmentToClientListFragment())
        }

        binding.navBtnVoorspelling.setOnClickListener { view: View ->
            view.findNavController().navigate(be.hogent.vic.screens.home.HomeFragmentDirections.actionHomeFragmentToVoorspellingFragment())
        }

        binding.navBtnAanvragen.setOnClickListener { view: View ->
            view.findNavController().navigate(be.hogent.vic.screens.home.HomeFragmentDirections.actionHomeFragmentToVirtualMachineRequestListFragment())
        }

        binding.btnProfile.setOnClickListener { view: View ->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }

        binding.dayNightSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }

        return binding.root
    }
}
