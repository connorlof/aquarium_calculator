package com.loftydev.aquariumcalculator.data.repository.datasourceimpl

import com.loftydev.aquariumcalculator.data.db.UnitSettingsDao
import com.loftydev.aquariumcalculator.data.model.UnitSystem
import com.loftydev.aquariumcalculator.data.repository.datasource.UnitSettingsLocalDataSource
import kotlinx.coroutines.flow.Flow

class UnitSettingsLocalDataSourceImpl(
    private val unitSettingsDao: UnitSettingsDao
): UnitSettingsLocalDataSource {
    override suspend fun saveUnitSetting(unitSystem: UnitSystem) {
        unitSettingsDao.insert(unitSystem)
    }

    override fun getUnitSetting(): Flow<List<UnitSystem>> {
        return unitSettingsDao.getUnitSetting()
    }
}