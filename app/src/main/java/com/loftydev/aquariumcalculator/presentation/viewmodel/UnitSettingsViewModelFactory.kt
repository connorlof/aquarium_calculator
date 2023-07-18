package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loftydev.aquariumcalculator.domain.usecase.GetUnitSettingsUseCase
import com.loftydev.aquariumcalculator.domain.usecase.SaveUnitSettingsUseCase

class UnitSettingsViewModelFactory(
    private val app: Application,
    private val getUnitSettingsUseCase: GetUnitSettingsUseCase,
    private val saveUnitSettingsUseCase: SaveUnitSettingsUseCase,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UnitSettingsViewModel(
            app,
            getUnitSettingsUseCase,
            saveUnitSettingsUseCase
        ) as T
    }
}