package com.loftydev.aquariumcalculator.domain.usecase

import com.loftydev.aquariumcalculator.data.model.UnitSystem
import com.loftydev.aquariumcalculator.domain.repository.UnitSettingsRepository
import kotlinx.coroutines.flow.Flow

class GetUnitSettingsUseCase(private val unitSettingsRepository: UnitSettingsRepository) {
    fun execute(): Flow<List<UnitSystem>> {
        return unitSettingsRepository.getUnitSetting()
    }
}