package com.loftydev.aquariumcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loftydev.aquariumcalculator.databinding.FragmentUnitConverterBinding
import com.loftydev.aquariumcalculator.domain.usecase.ConversionType
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitConverterViewModel

class UnitConverterFragment : Fragment() {

    lateinit var viewModel: UnitConverterViewModel
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
        setListeners()

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

    private fun convert() {
        // TODO: Handle invalid input
        val input = binding.etConvertInput.text.toString().toDouble()
        // TODO: Handle unit type dynamically
        val conversionType = ConversionType.FAHRENHEIT_TO_CELSIUS

        viewModel.convert(input, conversionType)
    }

}