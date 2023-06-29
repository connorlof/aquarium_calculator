package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loftydev.aquariumcalculator.domain.usecase.GetConversionUseCase
import com.loftydev.aquariumcalculator.domain.usecase.SwapConversionUseCase

class UnitConverterViewModelFactory(
    private val app: Application,
    private val getConversionUseCase: GetConversionUseCase,
    private val swapConversionUseCase: SwapConversionUseCase,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UnitConverterViewModel(
            app,
            getConversionUseCase,
            swapConversionUseCase
        ) as T
    }
}