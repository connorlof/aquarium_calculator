package com.loftydev.aquariumcalculator.domain.usecase

import com.loftydev.aquariumcalculator.data.model.UnitSystem
import com.loftydev.aquariumcalculator.domain.repository.UnitSettingsRepository

class SaveUnitSettingsUseCase(private val unitSettingsRepository: UnitSettingsRepository) {
    suspend fun execute(unitSystem: UnitSystem) = unitSettingsRepository.saveUnitSetting(unitSystem)
}