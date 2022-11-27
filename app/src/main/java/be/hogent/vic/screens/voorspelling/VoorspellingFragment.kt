package be.hogent.vic.screens.voorspelling

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import be.hogent.vic.R
import be.hogent.vic.databinding.FragmentVoorspellingBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * A simple [Fragment] subclass.
 * Use the [VoorspellingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VoorspellingFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    lateinit var viewModel : VoorspellingViewModel
    //TODO
    //-localDate gebruiken en day, month en year gebruiken voor de parse
    var datum: Date = Date()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         var binding: FragmentVoorspellingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_voorspelling, container, false
        )
        //of this
        viewModel = ViewModelProvider(requireActivity()).get(VoorspellingViewModel::class.java)

        binding.setLifecycleOwner (this)

       binding.voorspellingBtnDatum.setOnClickListener {
            var c = Calendar.getInstance()

            var year = c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)
            var day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { view, year, monthOfYear, dayOfMonth ->
                    datum = SimpleDateFormat("dd/MM/yyyy").parse("${dayOfMonth}/${monthOfYear+1}/${year}")
                    binding.voorspellingDatum.text = SimpleDateFormat("dd/MM/yyyy").format(datum)
                    viewModel.geefcpu(datum)
                },
                year,
                month,
                day
            )

           val minDay = c.get(Calendar.DAY_OF_MONTH)
           val minMonth = c.get(Calendar.MONTH)
           val minYear = c.get(Calendar.YEAR)
           c.set(minYear, minMonth, minDay)
           datePickerDialog.datePicker.minDate = c.timeInMillis

           val maxDay = c.get(Calendar.DAY_OF_MONTH)
           val maxMonth = c.get(Calendar.MONTH)
           val maxYear = c.get(Calendar.YEAR).plus(1)
           c.set(maxYear, maxMonth, maxDay)
           datePickerDialog.datePicker.maxDate = c.timeInMillis

           datePickerDialog.show()

        }

        //initeel iets tonen
        binding.voorspellingDatum.text =  SimpleDateFormat("dd/MM/yyyy").format(datum).toString()
        viewModel.geefcpu(datum)

        //cpu
        viewModel.totaalCPU.observe(viewLifecycleOwner, Observer {
            newCPU -> binding.voorspellingTotaalCpu.text = newCPU.toString()
        })

        viewModel.vrijCPU.observe(viewLifecycleOwner, Observer {
                newCPU -> binding.voorspellingVrijCpu.text = newCPU.toString()
        })

        viewModel.gebruikCPU.observe(viewLifecycleOwner, Observer {
                newCPU -> binding.voorspellingGebruikCpu.text = newCPU.toString()
        })

        //ram
        viewModel.totaalRAM.observe(viewLifecycleOwner, Observer {
                newRAM -> binding.voorspellingTotaalRam.text = newRAM.toString()
        })

        viewModel.vrijRAM.observe(viewLifecycleOwner, Observer {
                newRAM -> binding.voorspellingVrijRam.text = newRAM.toString()
        })

        viewModel.gebruikRAM.observe(viewLifecycleOwner, Observer {
                newRAM -> binding.voorspellingGebruikRam.text = newRAM.toString()
        })

        //storage
        viewModel.totaalStorage.observe(viewLifecycleOwner, Observer {
                newStorage -> binding.voorspellingTotaalStorage.text = newStorage.toString()
        })

        viewModel.vrijStorage.observe(viewLifecycleOwner, Observer {
                newStorage -> binding.voorspellingVrijStorage.text = newStorage.toString()
        })

        viewModel.gebruikStorage.observe(viewLifecycleOwner, Observer {
                newStorage -> binding.voorspellingGebruikStorage.text = newStorage.toString()
        })



        return binding.root

    }

}