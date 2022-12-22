package be.hogent.vic.screens.client

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import be.hogent.vic.databinding.FragmentClientBinding
import be.hogent.vic.domain.Client


class ClientFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentClientBinding.inflate(inflater)
        val selectedId = ClientFragmentArgs.fromBundle(requireArguments()).selectedId
        val viewModel = ViewModelProvider(
            this,
            ClientViewModel.Factory(requireNotNull(this.activity).application, selectedId)
        ).get(ClientViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.client.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    binding.client = it
                }
            }
        )

        binding.btnEmail.setOnClickListener(View.OnClickListener {
            val client : Client? = binding.client

            var emailString : String

            if(client != null
                && !client.email.isNullOrBlank()
                && "@" in client.email!!)
            {
                emailString = client.email!!
            }
            else
            {
                emailString = "test@company.com"
            }
            val mailto = "mailto:${emailString}" +
                    "?cc=" +
                    "&subject=" + Uri.encode("VIC") +
                    "&body=" + Uri.encode("Beste ")
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)
            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "Error to open email app", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }
}


