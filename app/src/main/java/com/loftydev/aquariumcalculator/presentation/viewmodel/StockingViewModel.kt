package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.loftydev.aquariumcalculator.domain.usecase.GetInchPerGallonUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetSurfaceAreaStockingUseCase

class StockingViewModel(
    app: Application,
    private val getInchPerGallonUseCase: GetInchPerGallonUseCase,
    private val getSurfaceAreaStockingUseCase: GetSurfaceAreaStockingUseCase,
) : AndroidViewModel(app) {

    fun calculateInchPerGallon(inchesOfFish: Double, tankGallons: Double) =
        getInchPerGallonUseCase.execute(inchesOfFish, tankGallons)

    fun calculateSurfaceArea(inchesOfFish: Double, surfaceAreaSqInches: Double) =
        getSurfaceAreaStockingUseCase.execute(inchesOfFish, surfaceAreaSqInches)

}