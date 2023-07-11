package com.loftydev.aquariumcalculator.data.repository.datasource

import com.loftydev.aquariumcalculator.data.model.UnitSystem
import kotlinx.coroutines.flow.Flow

interface UnitSettingsLocalDataSource {
    suspend fun saveUnitSetting(unitSystem: UnitSystem)
    fun getUnitSetting(): Flow<List<UnitSystem>>
}