package com.loftydev.aquariumcalculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    private fun setVolumeView(unitSystemType: UnitSystemType) {
        binding.switchVolume.isChecked = unitTypeIsMetric(unitSystemType)
    }

    private fun setDistanceView(unitSystemType: UnitSystemType) {
        binding.switchDist.isChecked = unitTypeIsMetric(unitSystemType)
    }

    private fun setListeners() {
        binding.switchTemp.setOnCheckedChangeListener { _, isSelected ->
            binding.tvTempUnit.text = toTemperatureUnit(isSelected.toUnitSystemType())
            saveSettings()
        }

        binding.switchVolume.setOnCheckedChangeListener { _, isSelected ->
            binding.tvVolUnit.text = toVolumeUnit(isSelected.toUnitSystemType())
            saveSettings()
        }

        binding.switchDist.setOnCheckedChangeListener { _, isSelected ->
            binding.tvDistUnit.text = toDistanceUnit(isSelected.toUnitSystemType())
            saveSettings()
        }
    }

    private fun saveSettings() {
        val selectedSettings = UnitSystem(
            temperature = binding.switchTemp.isChecked.toUnitSystemType(),
            volume = binding.switchVolume.isChecked.toUnitSystemType(),
            distance = binding.switchDist.isChecked.toUnitSystemType()
        )

        viewModel.saveUnitSettings(selectedSettings).observe(viewLifecycleOwner) {
            if (it != null) {
                Log.i("Aquarium_Calculator", "Unit settings saved")
            } else {
                Log.e("Aquarium_Calculator", "Error saving settings")
            }
        }
    }

    private fun Boolean.toUnitSystemType(): UnitSystemType {
        return when (this) {
            true -> UnitSystemType.METRIC
            false -> UnitSystemType.IMPERIAL
        }
    }

    private fun toTemperatureUnit(unitSystemType: UnitSystemType) =
        if (unitSystemType == UnitSystemType.METRIC) { "Celsius" }
        else { "Fahrenheit" }

    private fun toVolumeUnit(unitSystemType: UnitSystemType) =
        if (unitSystemType == UnitSystemType.METRIC) { "Liters" }
        else { "Gallons" }

    private fun toDistanceUnit(unitSystemType: UnitSystemType) =
        if (unitSystemType == UnitSystemType.METRIC) { "Millimeters" }
        else { "Inches" }

}