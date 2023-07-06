package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loftydev.aquariumcalculator.domain.usecase.GetInchPerGallonUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetSurfaceAreaStockingUseCase

class StockingViewModelFactory(
    private val app: Application,
    private val getInchPerGallonUseCase: GetInchPerGallonUseCase,
    private val getSurfaceAreaStockingUseCase: GetSurfaceAreaStockingUseCase,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StockingViewModel(
            app,
            getInchPerGallonUseCase,
            getSurfaceAreaStockingUseCase
        ) as T
    }

}