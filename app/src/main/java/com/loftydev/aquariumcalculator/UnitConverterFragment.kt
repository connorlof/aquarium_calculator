package com.loftydev.aquariumcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.loftydev.aquariumcalculator.databinding.FragmentUnitConverterBinding
import com.loftydev.aquariumcalculator.data.model.ConversionType
import com.loftydev.aquariumcalculator.presentation.viewmodel.MenuViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitConverterViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        setHeader()
        setUnits()
        observe()
        setListeners()
        setInitialConversionType()
    }

    @SuppressLint("SetTextI18n")
    private fun setHeader() {
        val header = when (viewModel.conversionType.value) {
            ConversionType.FAHRENHEIT_TO_CELSIUS -> "°F to °C"
            ConversionType.CELSIUS_TO_FAHRENHEIT -> "°C to °F"
            ConversionType.GALLONS_TO_LITERS -> "Liters to Gallons"
            ConversionType.LITERS_TO_GALLONS -> "Gallons to Liter"
            ConversionType.INCHES_TO_MM -> "Inches to MM"
            ConversionType.MM_TO_INCHES -> "MM to Inches"
            ConversionType.PPM_TO_DEGREES -> "PPM to °GH"
            ConversionType.DEGREES_TO_PPM -> "°GH to PPM"
            else -> "Unknown Units"
        }

        binding.tvConvertHeader.text = "Convert: $header"
    }

    @SuppressLint("SetTextI18n")
    private fun setUnits() {
        val units = when (viewModel.conversionType.value) {
            ConversionType.FAHRENHEIT_TO_CELSIUS -> Triple("Fahrenheit", "°F", "Celsius")
            ConversionType.CELSIUS_TO_FAHRENHEIT -> Triple("Celsius", "°C", "Fahrenheit")
            ConversionType.GALLONS_TO_LITERS -> Triple("Gallons", "g", "Liters")
            ConversionType.LITERS_TO_GALLONS -> Triple("Liters", "l", "Gallons")
            ConversionType.INCHES_TO_MM -> Triple("Inches", "in", "Millimeters")
            ConversionType.MM_TO_INCHES -> Triple("MM", "mm", "Inches")
            ConversionType.PPM_TO_DEGREES -> Triple("PPM", "ppm", "°GH")
            ConversionType.DEGREES_TO_PPM -> Triple("°GH", "°GH", "PPM")
            else -> Triple("Unknown", "??", "Error")
        }

        binding.tvConvertInputHint.text = units.first
        binding.tvConvertUnit.text = units.second
        binding.tvResultLabel.text = units.third
    }

    private fun observe() {
        viewModel.output.observe(viewLifecycleOwner) { output ->
            binding.tvConvertOutput.text = String.format("%.2f", output)
        }

        viewModel.conversionType.observe(viewLifecycleOwner) { type ->
            binding.tvConvertHeader.text = type.header
            setHeader()
            setUnits()
        }
    }

    private fun setListeners() {
        binding.btnConvertSwap.setOnClickListener {
            viewModel.swapUnits()
            swapInputOutput()
        }

        binding.etConvertInput.doOnTextChanged { _, _, _, _ ->
            MainScope().launch {
                delay(500)
                convert()
            }
        }
    }

    private fun swapInputOutput() {
        val oldOutput = binding.tvConvertOutput.text
        binding.etConvertInput.setText(oldOutput)
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