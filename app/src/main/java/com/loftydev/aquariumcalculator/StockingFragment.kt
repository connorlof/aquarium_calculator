package com.loftydev.aquariumcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.loftydev.aquariumcalculator.databinding.FragmentStockingBinding
import com.loftydev.aquariumcalculator.presentation.viewmodel.MenuViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.StockingViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class StockingFragment : Fragment() {

    lateinit var viewModel: StockingViewModel
    lateinit var menuViewModel: MenuViewModel
    private var _binding: FragmentStockingBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        _binding = FragmentStockingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MenuActivity).stockingViewModel
        menuViewModel = (activity as MenuActivity).menuViewModel

        viewModel.reset()
        setHeader()
        setHint()
        observe()
        setListeners()
    }

    private fun setHeader() {
        if (menuViewModel.lastSelectedItem.value == STOCKING_INCH_GALLON) {
            binding.tvStockingHeader.text = "Inch Per Gallon"
        } else {
            binding.tvStockingHeader.text = "Surface Area"
        }
    }

    private fun setHint() {
        if (menuViewModel.lastSelectedItem.value == STOCKING_INCH_GALLON) {
            binding.tvTankDimensionHint.text = "Tank Gallons"
        } else {
            binding.tvTankDimensionHint.text = "Tank Sq Inches"
        }
    }

    private fun observe() {
        viewModel.stockingPercent.observe(viewLifecycleOwner) { output ->
            binding.tvStockingOutput.text = formatStockingLevel(output)
            setStockingDescription(output)
        }
    }

    private fun formatStockingLevel(stockingPercent: Double): String =
        try {
            "${(stockingPercent * 100).roundToInt()} %"
        } catch (e: Exception) {
            "0 %"
        }

    private fun setStockingDescription(stockingPercent: Double) {
        if (stockingPercent < .85) {
            binding.tvResultDesc.text = "Healthy"
            binding.tvResultDesc.setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_widget))
            binding.ivTvResultInfo.tooltipText = "The amount of fish in your aquarium is at a healthy level."
        } else if (stockingPercent in 0.85..1.0) {
            binding.tvResultDesc.text = "Almost Full"
            binding.tvResultDesc.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary_card))
            binding.ivTvResultInfo.tooltipText = "Your tank is almost full and it is recommended to not add new fish."
        } else {
            binding.tvResultDesc.text = "Overstocked"
            binding.tvResultDesc.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_widget))
            binding.ivTvResultInfo.tooltipText = "Your tank is overstocked and there is risk of fish health being impacted."
        }
    }

    private fun setListeners() {
        binding.etFishInches.doOnTextChanged { _, _, _, _ ->
            MainScope().launch {
                delay(500)
                calculate()
            }
        }

        binding.etTankDimension.doOnTextChanged { _, _, _, _ ->
            MainScope().launch {
                delay(500)
                calculate()
            }
        }
    }

    private fun calculate() {
        val inchesOfFish = binding.etFishInches.text.toString().toDoubleOrNull() ?: 0.0

        if (menuViewModel.lastSelectedItem.value == STOCKING_INCH_GALLON) {
            val gallons = binding.etTankDimension.text.toString().toDoubleOrNull() ?: 0.0
            viewModel.calculateInchPerGallon(inchesOfFish, gallons)
        } else {
            val sqInches = binding.etTankDimension.text.toString().toDoubleOrNull() ?: 0.0
            viewModel.calculateSurfaceArea(inchesOfFish, sqInches)
        }
    }

    companion object {
        const val STOCKING_INCH_GALLON = "STOCKING_INCH_GALLON"
        const val STOCKING_SURFACE_AREA = "STOCKING_SURFACE_AREA"
    }
}