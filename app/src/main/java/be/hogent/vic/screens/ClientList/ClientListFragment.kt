package be.hogent.vic.screens.ClientList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.hogent.vic.databinding.FragmentClientListBinding

class ClientListFragment : Fragment() {
    private val viewModel: ClientListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }

        ViewModelProvider(
            this,
            ClientListViewModel.Factory(activity.application)
        ).get(ClientListViewModel::class.java)
    }

    private var viewModelAdapter: ClientAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentClientListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModelAdapter = ClientAdapter(
            ClientAdapter.OnClickListener {
                viewModel.displayDetails(it)
            }
        )
        binding.clientList.adapter = viewModelAdapter
        binding.viewModel = viewModel

        viewModel.navigateToDetails.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    this.findNavController().navigate(ClientListFragmentDirections.actionClientListFragmentToClientFragment(it))
                    viewModel.displayDetailsComplete()
                }
            }
        )

        return binding.root
    }
}
