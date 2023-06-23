package com.loftydev.aquariumcalculator.domain.usecase

import com.loftydev.aquariumcalculator.data.model.FilterResponse
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.repository.EquipmentRepository

class GetFiltersUseCase(private val equipmentRepository: EquipmentRepository) {
    suspend fun execute(): Resource<FilterResponse> {
        return equipmentRepository.getFilters()
    }
}