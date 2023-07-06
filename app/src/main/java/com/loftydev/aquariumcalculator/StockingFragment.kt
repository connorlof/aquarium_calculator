package com.loftydev.aquariumcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loftydev.aquariumcalculator.databinding.FragmentStockingBinding
import com.loftydev.aquariumcalculator.presentation.viewmodel.MenuViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.StockingViewModel

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
    }

    companion object {
        const val STOCKING_INCH_GALLON = "STOCKING_INCH_GALLON"
        const val STOCKING_SURFACE_AREA = "STOCKING_SURFACE_AREA"
    }
}