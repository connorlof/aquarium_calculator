package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loftydev.aquariumcalculator.domain.usecase.GetConversionUseCase
import com.loftydev.aquariumcalculator.domain.usecase.SwapConversionUseCase

class StockingViewModelFactory(
    private val app: Application,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StockingViewModel(
            app
        ) as T
    }
}