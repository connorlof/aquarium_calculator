package com.loftydev.aquariumcalculator.domain.usecase

import com.loftydev.aquariumcalculator.data.model.UnitSystem
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
import com.loftydev.aquariumcalculator.domain.repository.UnitSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty

class GetUnitSettingsUseCase(private val unitSettingsRepository: UnitSettingsRepository) {
    fun execute(): Flow<UnitSystem> {
        return unitSettingsRepository
            .getUnitSetting()
            .map {
                if (it.isEmpty()) {
                    DEFAULT_UNIT_SETTING
                } else {
                    it.last()
                }
            }
            .onEmpty { DEFAULT_UNIT_SETTING }
    }

    companion object {
        val DEFAULT_UNIT_SETTING = UnitSystem(0, UnitSystemType.IMPERIAL, UnitSystemType.IMPERIAL, UnitSystemType.IMPERIAL)
    }
}