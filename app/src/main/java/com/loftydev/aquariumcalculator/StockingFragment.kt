package com.loftydev.aquariumcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.widget.doOnTextChanged
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
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
        setUnits()
        observe()
        setListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun setHeader() {
        if (menuViewModel.lastSelectedItem.value == STOCKING_INCH_GALLON) {
            binding.tvStockingHeader.text = "Inch Per Gallon"
        } else {
            binding.tvStockingHeader.text = "Surface Area"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setHint() {
        if (menuViewModel.lastSelectedItem.value == STOCKING_INCH_GALLON) {
            binding.tvTankDimensionHint.text = "Volume"
        } else {
            binding.tvTankDimensionHint.text = "Surface Area"
        }
    }

    private fun setUnits() {
        if (menuViewModel.lastSelectedItem.value == STOCKING_INCH_GALLON) {

            binding.tvDistanceUnit.text = distanceUnit()
            binding.tvTankDimensionUnit.text = volumeUnit()
        } else {
            binding.tvDistanceUnit.text = distanceUnit()
            binding.tvTankDimensionUnit.text = surfaceAreaUnit()
        }
    }

    private fun distanceUnit() =
        if (viewModel.distanceUnit.value == UnitSystemType.METRIC) { "mm" }
        else { "in" }

    private fun volumeUnit() =
        if (viewModel.volumeUnit.value == UnitSystemType.METRIC) { "l" }
        else { "g" }

    private fun surfaceAreaUnit() =
        if (viewModel.distanceUnit.value == UnitSystemType.METRIC) {
            HtmlCompat.fromHtml("mm&#xB2;", HtmlCompat.FROM_HTML_MODE_LEGACY)
        } else {
            HtmlCompat.fromHtml("in&#xB2;", HtmlCompat.FROM_HTML_MODE_LEGACY)
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

    @SuppressLint("SetTextI18n")
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
        binding.etFishDistance.doOnTextChanged { _, _, _, _ ->
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
        val fishDistance = binding.etFishDistance.text.toString().toDoubleOrNull() ?: 0.0

        if (menuViewModel.lastSelectedItem.value == STOCKING_INCH_GALLON) {
            val volume = binding.etTankDimension.text.toString().toDoubleOrNull() ?: 0.0
            viewModel.calculateInchPerGallon(fishDistance, volume)
        } else {
            val surfaceArea = binding.etTankDimension.text.toString().toDoubleOrNull() ?: 0.0
            viewModel.calculateSurfaceArea(fishDistance, surfaceArea)
        }
    }

    companion object {
        const val STOCKING_INCH_GALLON = "STOCKING_INCH_GALLON"
        const val STOCKING_SURFACE_AREA = "STOCKING_SURFACE_AREA"
    }
}