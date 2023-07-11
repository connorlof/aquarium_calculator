package com.loftydev.aquariumcalculator.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.loftydev.aquariumcalculator.data.model.UnitSystem
import kotlinx.coroutines.flow.Flow

@Dao
interface UnitSettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(unitSystem: UnitSystem)

    @Query("SELECT * FROM units")
    fun getUnitSetting(): Flow<List<UnitSystem>>
}