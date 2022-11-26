package be.hogent.vic.screens.voorspelling

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import be.hogent.vic.R
import be.hogent.vic.databinding.FragmentVoorspellingBinding
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
    var datum: Date = SimpleDateFormat("yyyy/MM/dd").parse("2022/11/25")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentVoorspellingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_voorspelling, container, false
        )
        viewModel = ViewModelProvider(this).get(VoorspellingViewModel::class.java)

        binding.setLifecycleOwner (this)

       binding.voorspellingBtnDatum.setOnClickListener {
            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { view, year, monthOfYear, dayOfMonth ->
                    binding.voorspellingDatum.text = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    datum = SimpleDateFormat("yyyy/MM/dd").parse("${year}/${monthOfYear}/${dayOfMonth}")
                    binding.voorspellingCpu.text = "${SimpleDateFormat("yyyy/MM/dd").parse("${year}/${monthOfYear}/${dayOfMonth}")}"
                },
                year,
                month,
                day
            )

           val minDay = 25
           val minMonth = 11
           val minYear = 2022
           c.set(minYear, minMonth, minDay)
           datePickerDialog.datePicker.minDate = c.timeInMillis

           val maxDay = 26
           val maxMonth = 11
           val maxYear = 2022
           c.set(maxYear, maxMonth, maxDay)
           datePickerDialog.datePicker.maxDate = c.timeInMillis

            datePickerDialog.show()

        }

        binding.voorspellingCpu.text = viewModel.geefcpu(datum)

        return binding.root

    }

    //TODO
    //functies voor voorspelling
    //-viewmodel voorspelling
    //databinding voor functie variabele
    //3 delen tonen (cpu, ram, opslag)

}