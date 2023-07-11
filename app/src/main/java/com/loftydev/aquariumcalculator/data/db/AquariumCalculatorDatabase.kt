package com.loftydev.aquariumcalculator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.loftydev.aquariumcalculator.data.model.UnitSystem

@Database(
    entities = [UnitSystem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AquariumCalculatorDatabase : RoomDatabase() {
    abstract fun getUnitSettingsDao(): UnitSettingsDao
}