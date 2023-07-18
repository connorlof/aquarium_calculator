package com.loftydev.aquariumcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.loftydev.aquariumcalculator.data.model.UnitSystem
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
import com.loftydev.aquariumcalculator.databinding.FragmentUnitSettingsBinding
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitSettingsViewModel

class UnitSettingsFragment : Fragment() {

    lateinit var viewModel: UnitSettingsViewModel
    private var _binding: FragmentUnitSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        _binding = FragmentUnitSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MenuActivity).unitSettingsViewModel

        loadSettings()
        setListeners()
    }

    private fun loadSettings() {
        val settingsLiveData = viewModel.getUnitSettings()

        settingsLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                setTemperatureView(it.temperature)
                setVolumeView(it.volume)
                setDistanceView(it.distance)
            }
        }
    }

    private fun unitTypeIsMetric(unitSystemType: UnitSystemType) = unitSystemType == UnitSystemType.METRIC

    private fun setTemperatureView(unitSystemType: UnitSystemType) {
        binding.switchTemp.isChecked = unitTypeIsMetric(unitSystemType)
        binding.switchTemp.text = unitSystemType.name
    }

    private fun setVolumeView(unitSystemType: UnitSystemType) {
        binding.switchVolume.isChecked = unitTypeIsMetric(unitSystemType)
        binding.switchVolume.text = unitSystemType.name
    }

    private fun setDistanceView(unitSystemType: UnitSystemType) {
        binding.switchDist.isChecked = unitTypeIsMetric(unitSystemType)
        binding.switchDist.text = unitSystemType.name
    }

    private fun setListeners() {
        binding.switchTemp.setOnCheckedChangeListener { _, isSelected ->
            binding.switchTemp.text = isSelected.toUnitSystemType().name
        }

        binding.switchVolume.setOnCheckedChangeListener { _, isSelected ->
            binding.switchVolume.text = isSelected.toUnitSystemType().name
        }

        binding.switchDist.setOnCheckedChangeListener { _, isSelected ->
            binding.switchDist.text = isSelected.toUnitSystemType().name
        }

        binding.btnSaveSettings.setOnClickListener {
            val selectedSettings = UnitSystem(
                temperature = binding.switchTemp.isChecked.toUnitSystemType(),
                volume = binding.switchVolume.isChecked.toUnitSystemType(),
                distance = binding.switchDist.isChecked.toUnitSystemType()
            )

            // TODO: Replace with snackbar
            viewModel.saveUnitSettings(selectedSettings).observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(context, "Settings saved!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error saving", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun Boolean.toUnitSystemType(): UnitSystemType {
        return when (this) {
            true -> UnitSystemType.METRIC
            false -> UnitSystemType.IMPERIAL
        }
    }
}