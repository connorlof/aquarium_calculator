package com.loftydev.aquariumcalculator.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.loftydev.aquariumcalculator.data.model.UnitSystem
import com.loftydev.aquariumcalculator.domain.usecase.GetUnitSettingsUseCase
import com.loftydev.aquariumcalculator.domain.usecase.SaveUnitSettingsUseCase

class UnitSettingsViewModel(
    private val getUnitSettingsUseCase: GetUnitSettingsUseCase,
    private val saveUnitSettingsUseCase: SaveUnitSettingsUseCase,
) : ViewModel() {

    fun saveUnitSettings(unitSettings: UnitSystem) = liveData {
        val settingsList = saveUnitSettingsUseCase.execute(unitSettings)
        emit(settingsList)
    }

    fun getUnitSettings() = liveData {
        getUnitSettingsUseCase.execute().collect {
            emit(it)
        }
    }
}