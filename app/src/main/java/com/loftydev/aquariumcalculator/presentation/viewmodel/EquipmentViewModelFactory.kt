package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetHeatersUseCase

class EquipmentViewModelFactory(
    private val app: Application,
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getHeatersUseCase: GetHeatersUseCase,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EquipmentViewModel(
            app,
            getFiltersUseCase,
            getHeatersUseCase
        ) as T
    }
}
