package com.loftydev.aquariumcalculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "units")
data class UnitSystem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val temperature: UnitSystemType,
    val volume: UnitSystemType,
    val distance: UnitSystemType
)