package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loftydev.aquariumcalculator.domain.usecase.GetInchPerGallonUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetSurfaceAreaStockingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockingViewModel(
    app: Application,
    private val getInchPerGallonUseCase: GetInchPerGallonUseCase,
    private val getSurfaceAreaStockingUseCase: GetSurfaceAreaStockingUseCase,
) : AndroidViewModel(app) {

    val stockingPercent: MutableLiveData<Double> = MutableLiveData()

    fun reset() = viewModelScope.launch(Dispatchers.IO) {
        stockingPercent.postValue(0.0)
    }

    fun calculateInchPerGallon(inchesOfFish: Double, tankGallons: Double) =
        viewModelScope.launch(Dispatchers.IO) {
            stockingPercent.postValue(getInchPerGallonUseCase.execute(inchesOfFish, tankGallons))
        }

    fun calculateSurfaceArea(inchesOfFish: Double, surfaceAreaSqInches: Double) =
        viewModelScope.launch(Dispatchers.IO) {
            stockingPercent.postValue(getSurfaceAreaStockingUseCase.execute(inchesOfFish, surfaceAreaSqInches))
        }

}