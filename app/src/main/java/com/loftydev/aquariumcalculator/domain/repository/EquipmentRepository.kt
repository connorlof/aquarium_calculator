package com.loftydev.aquariumcalculator.domain.repository

import com.loftydev.aquariumcalculator.data.model.FilterResponse
import com.loftydev.aquariumcalculator.data.util.Resource

interface EquipmentRepository {
    suspend fun getFilters(): Resource<FilterResponse>
}