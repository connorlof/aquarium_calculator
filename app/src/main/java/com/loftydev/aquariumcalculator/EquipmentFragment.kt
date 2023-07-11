package com.loftydev.aquariumcalculator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.databinding.FragmentEquipmentBinding
import com.loftydev.aquariumcalculator.presentation.adapter.EquipmentAdapter
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModel

class EquipmentFragment : Fragment() {

    lateinit var viewModel: EquipmentViewModel
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

        initRecyclerView()
        viewFiltersList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        equipmentAdapter = EquipmentAdapter(parentContext)
        binding.rvEquipment.apply {
            adapter = equipmentAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun viewFiltersList() {
        viewModel.getFilters()
        viewModel.filters.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        equipmentAdapter.differ.submitList(it)
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