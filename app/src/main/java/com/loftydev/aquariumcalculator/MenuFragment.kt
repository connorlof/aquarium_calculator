package com.loftydev.aquariumcalculator

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.loftydev.aquariumcalculator.databinding.FragmentMenuBinding
import com.loftydev.aquariumcalculator.presentation.viewmodel.MenuViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitConverterViewModel

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    private val binding get() = _binding!!

    lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        viewModel = (activity as MenuActivity).menuViewModel

        binding.filterEquipButton.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_EquipmentFragment)
        }

        binding.tempConvButton.setOnClickListener {
            viewModel.select(UnitConverterFragment.UNIT_CONVERT_TEMPERATURE)
            findNavController().navigate(R.id.action_MenuFragment_to_unitConverterFragment)
        }

        binding.volConvButton.setOnClickListener {
            viewModel.select(UnitConverterFragment.UNIT_CONVERT_VOLUME)
            findNavController().navigate(R.id.action_MenuFragment_to_unitConverterFragment)
        }

        binding.distConvButton.setOnClickListener {
            viewModel.select(UnitConverterFragment.UNIT_CONVERT_DISTANCE)
            findNavController().navigate(R.id.action_MenuFragment_to_unitConverterFragment)
        }

        binding.hardnessConvButton.setOnClickListener {
            viewModel.select(UnitConverterFragment.UNIT_CONVERT_HARDNESS)
            findNavController().navigate(R.id.action_MenuFragment_to_unitConverterFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}