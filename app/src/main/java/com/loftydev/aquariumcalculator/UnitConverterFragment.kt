package com.loftydev.aquariumcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loftydev.aquariumcalculator.databinding.FragmentUnitConverterBinding
import com.loftydev.aquariumcalculator.data.model.ConversionType
import com.loftydev.aquariumcalculator.presentation.viewmodel.MenuViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitConverterViewModel

class UnitConverterFragment : Fragment() {

    lateinit var viewModel: UnitConverterViewModel
    lateinit var menuViewModel: MenuViewModel
    private var _binding: FragmentUnitConverterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        _binding = FragmentUnitConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MenuActivity).unitConverterViewModel
        menuViewModel = (activity as MenuActivity).menuViewModel

        observe()
        setListeners()
        setInitialConversionType()
    }

    private fun observe() {
        viewModel.output.observe(viewLifecycleOwner) { output ->
            binding.tvConvertOutput.text = output.toString()
        }

        viewModel.conversionType.observe(viewLifecycleOwner) { type ->
            binding.tvConvertHeader.text = type.header
        }
    }

    private fun setListeners() {
        binding.btnConvertCalculate.setOnClickListener { convert() }
        binding.btnConvertSwap.setOnClickListener {
            viewModel.swapUnits()
        }
    }

    private fun setInitialConversionType() {
        val conversionType = when (menuViewModel.lastSelectedItem.value) {
            UNIT_CONVERT_TEMPERATURE -> ConversionType.FAHRENHEIT_TO_CELSIUS
            UNIT_CONVERT_VOLUME -> ConversionType.GALLONS_TO_LITERS
            UNIT_CONVERT_DISTANCE -> ConversionType.INCHES_TO_MM
            UNIT_CONVERT_HARDNESS -> ConversionType.PPM_TO_DEGREES
            else -> ConversionType.FAHRENHEIT_TO_CELSIUS
        }

        viewModel.setConversionType(conversionType)
    }

    private fun convert() {
        val input = binding.etConvertInput.text.toString().toDoubleOrNull() ?: 0.0
        val conversionType = viewModel.conversionType.value ?: ConversionType.FAHRENHEIT_TO_CELSIUS
        viewModel.convert(input, conversionType)
    }

    companion object {
        const val UNIT_CONVERT_TEMPERATURE = "UNIT_CONVERT_TEMPERATURE"
        const val UNIT_CONVERT_VOLUME = "UNIT_CONVERT_VOLUME"
        const val UNIT_CONVERT_DISTANCE = "UNIT_CONVERT_DISTANCE"
        const val UNIT_CONVERT_HARDNESS = "UNIT_CONVERT_HARDNESS"
    }

}