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

class VoorspellingFragment : Fragment() {
    private lateinit var binding: FragmentVoorspellingBinding
    private lateinit var viewModel: VoorspellingViewModel

    var datum: Date = Date()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVoorspellingBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(VoorspellingViewModel::class.java)

        binding.voorspelling = viewModel
        binding.lifecycleOwner = this

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
                    viewModel.doeVoorspelling(datum)
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

        binding.voorspellingDatum.text =  SimpleDateFormat("dd/MM/yyyy").format(datum).toString()
        viewModel.doeVoorspelling(datum)

        return binding.root
    }

}