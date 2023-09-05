package com.loftydev.aquariumcalculator.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetHeatersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetUnitSettingsUseCase

class EquipmentViewModelFactory(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getHeatersUseCase: GetHeatersUseCase,
    private val getUnitSettingsUseCase: GetUnitSettingsUseCase,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EquipmentViewModel(
            getFiltersUseCase,
            getHeatersUseCase,
            getUnitSettingsUseCase
        ) as T
    }
}
