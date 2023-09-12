package com.loftydev.aquariumcalculator.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
import com.loftydev.aquariumcalculator.domain.usecase.GetInchPerGallonUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetSurfaceAreaStockingUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetUnitSettingsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockingViewModel(
    private val getInchPerGallonUseCase: GetInchPerGallonUseCase,
    private val getSurfaceAreaStockingUseCase: GetSurfaceAreaStockingUseCase,
    private val getUnitSettingsUseCase: GetUnitSettingsUseCase,
) : ViewModel() {

    val stockingPercent: MutableLiveData<Double> = MutableLiveData(0.0)
    val distanceUnit: MutableLiveData<UnitSystemType> = MutableLiveData(UnitSystemType.IMPERIAL)
    val volumeUnit: MutableLiveData<UnitSystemType> = MutableLiveData(UnitSystemType.IMPERIAL)

    fun reset() = viewModelScope.launch(Dispatchers.IO) {
        stockingPercent.postValue(0.0)
        loadUnitSettings()
    }

    private fun loadUnitSettings() = viewModelScope.launch(Dispatchers.IO) {
        getUnitSettingsUseCase.execute().collect {
            distanceUnit.postValue(it.distance)
            volumeUnit.postValue(it.volume)
        }
    }

    fun calculateInchPerGallon(fishDistance: Double, tankVolume: Double) =
        viewModelScope.launch(Dispatchers.IO) {
            val distanceUnit = distanceUnit.value ?: UnitSystemType.IMPERIAL
            val volumeUnit = volumeUnit.value ?: UnitSystemType.IMPERIAL
            stockingPercent.postValue(getInchPerGallonUseCase.execute(fishDistance, distanceUnit, tankVolume, volumeUnit))
        }

    fun calculateSurfaceArea(fishDistance: Double, surfaceArea: Double) =
        viewModelScope.launch(Dispatchers.IO) {
            val distanceUnit = distanceUnit.value ?: UnitSystemType.IMPERIAL
            stockingPercent.postValue(getSurfaceAreaStockingUseCase.execute(fishDistance, surfaceArea, distanceUnit))
        }

}