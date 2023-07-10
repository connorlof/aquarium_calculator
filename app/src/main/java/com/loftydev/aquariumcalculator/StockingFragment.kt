package com.loftydev.aquariumcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            binding.tvTankDimensionHint.text = "Tank Sq Inches (Length x Depth)"
        }
    }

    private fun observe() {
        viewModel.stockingPercent.observe(viewLifecycleOwner) { output ->
            binding.tvStockingOutput.text = "Stocking Level: ${(output * 100).roundToInt()}%"
        }
    }

    private fun setListeners() {
        binding.btnStockingCalculate.setOnClickListener { calculate() }

        binding.etFishInches.doOnTextChanged { _, _, _, _ ->
            MainScope().launch {
                delay(1000)
                calculate()
            }
        }

        binding.etTankDimension.doOnTextChanged { _, _, _, _ ->
            MainScope().launch {
                delay(1000)
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