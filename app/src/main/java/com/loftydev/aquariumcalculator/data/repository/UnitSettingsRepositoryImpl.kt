package com.loftydev.aquariumcalculator.data.repository

import com.loftydev.aquariumcalculator.data.model.UnitSystem
import com.loftydev.aquariumcalculator.data.repository.datasource.UnitSettingsLocalDataSource
import com.loftydev.aquariumcalculator.domain.repository.UnitSettingsRepository
import kotlinx.coroutines.flow.Flow

class UnitSettingsRepositoryImpl(
    private val unitSettingsLocalDataSource: UnitSettingsLocalDataSource
): UnitSettingsRepository {
    override suspend fun saveUnitSetting(unitSystem: UnitSystem) {
        unitSettingsLocalDataSource.saveUnitSetting(unitSystem)
    }

    override fun getUnitSetting(): Flow<List<UnitSystem>> {
        return unitSettingsLocalDataSource.getUnitSetting()
    }
}