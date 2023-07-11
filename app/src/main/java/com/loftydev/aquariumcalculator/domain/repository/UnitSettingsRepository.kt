package com.loftydev.aquariumcalculator.domain.repository

import com.loftydev.aquariumcalculator.data.model.UnitSystem
import kotlinx.coroutines.flow.Flow

interface UnitSettingsRepository {
    suspend fun saveUnitSetting(unitSystem: UnitSystem)
    fun getUnitSetting(): Flow<List<UnitSystem>>
}