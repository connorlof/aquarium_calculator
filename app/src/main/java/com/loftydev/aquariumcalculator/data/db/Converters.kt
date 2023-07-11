package com.loftydev.aquariumcalculator.data.db

import androidx.room.TypeConverter
import com.loftydev.aquariumcalculator.data.model.UnitSystemType

class Converters {
    @TypeConverter
    fun toUnitSystemType(unitSystemType: String) = UnitSystemType.valueOf(unitSystemType)

    @TypeConverter
    fun fromUnitSystemType(unitSystemType: UnitSystemType) = unitSystemType.name
}