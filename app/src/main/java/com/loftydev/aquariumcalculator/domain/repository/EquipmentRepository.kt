package com.loftydev.aquariumcalculator.domain.repository

import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import com.loftydev.aquariumcalculator.data.util.Resource

interface EquipmentRepository {
    suspend fun getFilters(): Resource<EquipmentResponse>
    suspend fun getHeaters(): Resource<EquipmentResponse>
}