package com.loftydev.aquariumcalculator

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.databinding.FragmentEquipmentBinding
import com.loftydev.aquariumcalculator.presentation.adapter.EquipmentAdapter
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.MenuViewModel
import kotlin.math.roundToInt

class EquipmentFragment : Fragment() {

    lateinit var viewModel: EquipmentViewModel
    lateinit var menuViewModel: MenuViewModel
    private lateinit var parentContext: Context
    private lateinit var equipmentAdapter: EquipmentAdapter
    private var _binding: FragmentEquipmentBinding? = null
    private var isLoading = false

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquipmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MenuActivity).equipmentViewModel
        menuViewModel = (activity as MenuActivity).menuViewModel

        viewModel.reset()
        setHeader()
        observeUnits()
        initRecyclerView()
        viewEquipmentList()
        setSearchView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun setHeader() {
        if (menuViewModel.lastSelectedItem.value == EQUIPMENT_FILTER) {
            binding.tvEquipmentHeader.text = "Filter Recommendations"
        } else {
            binding.tvEquipmentHeader.text = "Heater Recommendations"
        }
    }

    private fun observeUnits() {
        viewModel.volumeUnit.observe(viewLifecycleOwner) { volumeUnit ->
            if (volumeUnit == UnitSystemType.METRIC) {
                binding.tvEquipUnit.text = "l"
            } else {
                binding.tvEquipUnit.text = "g"
            }
        }
    }

    private fun initRecyclerView() {
        equipmentAdapter = EquipmentAdapter(parentContext)
        binding.rvEquipment.apply {
            adapter = equipmentAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun viewEquipmentList() {
        viewModel.reset()
        if (menuViewModel.lastSelectedItem.value == EQUIPMENT_FILTER) {
            viewFiltersList()
        } else {
            viewHeatersList()
        }
    }

    private fun viewFiltersList() {
        viewModel.getFilters()
        viewModel.filters.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        equipmentAdapter.addData(it)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        // TODO: Snackbar for error
                        Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun viewHeatersList() {
        viewModel.getHeaters()
        viewModel.heaters.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        equipmentAdapter.addData(it)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        // TODO: Snackbar for error
                        Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun setSearchView() {
        binding.etEquipInput.doOnTextChanged { text, _, _, _ ->
            val filterForGallons = if (viewModel.volumeUnit.value == UnitSystemType.METRIC) {
                val liters = text.toString().toDoubleOrNull() ?: 0.0
                val gallons = liters / 3.785
                gallons.roundToInt().toString()
            } else text

            equipmentAdapter.filter.filter(filterForGallons)
        }
    }

    private fun showProgressBar() {
        isLoading = true
        binding.pbEquipment.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.pbEquipment.visibility = View.GONE
    }

    companion object {
        const val EQUIPMENT_FILTER = "FILTER"
        const val EQUIPMENT_HEATER = "HEATER"
    }
}