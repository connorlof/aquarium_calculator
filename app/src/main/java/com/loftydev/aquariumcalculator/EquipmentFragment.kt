package com.loftydev.aquariumcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.databinding.FragmentEquipmentBinding
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModel

class EquipmentFragment : Fragment() {

    lateinit var viewModel: EquipmentViewModel
    private var _binding: FragmentEquipmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquipmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MenuActivity).equipmentViewModel
        viewFiltersList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun viewFiltersList() {
        viewModel.getFilters()
        viewModel.filters.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    // TODO: Add progress bar to view
                    //hideProgressBar()
                    response.data?.let {
                        binding.tvEquipResult.text = it.toString()
                    }
                }

                is Resource.Error -> {
                    //hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    binding.tvEquipResult.text = "Loading"
                    //showProgressBar()
                }
            }
        }
    }
}